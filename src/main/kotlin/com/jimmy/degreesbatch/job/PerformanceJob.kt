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

        var performanceAPIUrl =
            "${KOPIS_PERFORMANCE}service=${KOPIS_APIKEY}&stdate=20220705&eddate=202201231&cpage=1&rows=1000"

        var xmlModule = JacksonXmlModule()
        xmlModule.setDefaultUseWrapper(false)

        var xmlConfigure = XmlMapper(xmlModule).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        var performanceURL = URL(performanceAPIUrl)
        var performanceResultResponse = xmlConfigure.readValue(performanceURL, ResultResponse::class.java)

        if (performanceResultResponse != null) {
            for (i in performanceResultResponse.db?.indices!!) {
                var performanceDetailAPIUrl = KOPIS_PERFORMANCE_DETAIL + performanceResultResponse.db!![i].mt20id + "/?service=${KOPIS_APIKEY}"
                var performanceDetailURL = URL(performanceDetailAPIUrl)
                var performanceDetailResultResponse = xmlConfigure.readValue(performanceDetailURL, ResultResponseDetail::class.java)

                performanceDetailResultResponse.db?.let { performanceService.insertPerformance(it) }
            }
        }
    }

    @Scheduled(cron = "*/20 * * * * *")
    private fun getFacility() {

//        facilityService.deleteFacility()

        var facilities: List<String> = performanceService.selectDistinctFC()

        var xmlModule = JacksonXmlModule()
        xmlModule.setDefaultUseWrapper(false)

        var xmlConfigure = XmlMapper(xmlModule).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        for (i in facilities.indices) {
            var facilityAPIUrl = "${KOPIS_FACILITY}${facilities[i]}?service=${KOPIS_APIKEY}"
            var url = URL(facilityAPIUrl)
            var facilityResultResponse = xmlConfigure.readValue(url, FacilityResponse::class.java)
            if (facilityResultResponse != null) {
                facilityResultResponse.db?.let { facilityService.insertFacility(it) }
            }
        }
    }
}
