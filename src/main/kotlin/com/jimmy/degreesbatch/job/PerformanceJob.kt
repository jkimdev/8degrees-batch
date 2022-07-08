package com.jimmy.degreesbatch.job

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.jimmy.degreesbatch.response.FacilityResponse
import com.jimmy.degreesbatch.response.ResultResponse
import com.jimmy.degreesbatch.response.ResultResponseDetail
import com.jimmy.degreesbatch.service.FacilityService
import com.jimmy.degreesbatch.service.PerformanceService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.net.URL

@Component
@Slf4j
class PerformanceJob(performanceService: PerformanceService, facilityService: FacilityService) {


    private var performanceService: PerformanceService = PerformanceService();
    private var facilityService: FacilityService = FacilityService();

    init {
        this.performanceService = performanceService
        this.facilityService = facilityService
    }

    @Value("\${kopis.apikey}")
    lateinit var KOPIS_APIKEY: String;

    @Value("\${kopis.performance}")
    lateinit var KOPIS_PERFORMANCE: String

    @Value("\${kopis.performance.detail}")
    lateinit var KOPIS_PERFORMANCE_DETAIL: String

    @Value("\${kopis.facility}")
    lateinit var KOPIS_FACILITY: String


    @Scheduled(cron = "*/10 * * * * *")
    private fun getPerformance() {

        performanceService.deletePerformance()

        var apiUrl =
            "${KOPIS_PERFORMANCE}service=${KOPIS_APIKEY}&stdate=20220705&eddate=202201231&cpage=1&rows=1000"

        var module = JacksonXmlModule()
        module.setDefaultUseWrapper(false)

        var xm = XmlMapper(module).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        var url = URL(apiUrl)
        var resultResponse = xm.readValue(url, ResultResponse::class.java)

        if (resultResponse != null) {
            for (i in resultResponse.db?.indices!!) {
                var apiUrl2 = KOPIS_PERFORMANCE_DETAIL + resultResponse.db!![i].mt20id + "/?service=${KOPIS_APIKEY}"
                var xm = XmlMapper(module).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                var url2 = URL(apiUrl2)
                var detailResultResponse = xm.readValue(url2, ResultResponseDetail::class.java)

                detailResultResponse.db?.let { performanceService.insertPerformance(it) }
            }
        }
    }

    @Scheduled(cron = "*/15 * * * * *")
    private fun getFacility() {

        facilityService.deleteFacility()

        var fcList: List<String> = performanceService.selectDistinctFC()

        var module = JacksonXmlModule()
        module.setDefaultUseWrapper(false)

        var xm = XmlMapper(module).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        for (i in fcList.indices) {
            var apiUrl = "${KOPIS_FACILITY}${fcList[i]}?service=${KOPIS_APIKEY}"
            var url = URL(apiUrl)
            var resultResponse = xm.readValue(url, FacilityResponse::class.java)
            if (resultResponse != null) {
                resultResponse.db?.let { facilityService.insertFacility(it) }
            }
        }
    }
}
