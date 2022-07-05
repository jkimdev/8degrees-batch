package com.jimmy.degreesbatch.job

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.jimmy.degreesbatch.response.ResultResponseDetail
import com.jimmy.degreesbatch.response.ResultResponse
import com.jimmy.degreesbatch.service.PerformanceService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.net.URL

@Component
@Slf4j
class PerformanceJob(performanceService: PerformanceService) {


   private var performanceService: PerformanceService = PerformanceService();

    init {
        this.performanceService = performanceService
    }

    @Value("\${kopis.apikey}")
    lateinit var KOPIS_APIKEY: String;
    @Value("\${kopis.performance.list}")
    lateinit var KOPIS_PERFORMANCE_LIST: String
    @Value("\${kopis.performance.detail}")
    lateinit var KOPIS_PERFORMANCE_DETAIL: String


    @Scheduled(cron = "*/10 * * * * *")
    private fun getPerformance() {
//       var result:List<PerformanceDto> = performanceService.selectPerformance()
        var apiUrl = "${KOPIS_PERFORMANCE_LIST}service=${KOPIS_APIKEY}&stdate=20220705&eddate=20220715&cpage=1&rows=5"

        var module = JacksonXmlModule()
        module.setDefaultUseWrapper(false)

        var om = XmlMapper(module).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        var url = URL(apiUrl)
        var resultResponse = om.readValue(url, ResultResponse::class.java)

        performanceService.deletePerformance()

        if (resultResponse != null) {
            for (i in resultResponse.db?.indices!!) {
              var apiUrl2 = KOPIS_PERFORMANCE_DETAIL + resultResponse.db!![i].mt20id + "/?service=${KOPIS_APIKEY}"
              var xm = XmlMapper(module).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                var url2 = URL(apiUrl2)
                var detailResultResponse = xm.readValue(url2, ResultResponseDetail::class.java)

                detailResultResponse.db?.let { performanceService.insertPerformance(it) }
            }


        }


//        if (resultResponse != null) {
//            resultResponse.db?.let { performanceService.insertPerformance(it) }
//        }
    }
}