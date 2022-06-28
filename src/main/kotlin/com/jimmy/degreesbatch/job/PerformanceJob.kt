package com.jimmy.degreesbatch.job

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.jimmy.degreesbatch.response.ResultResponse
import com.jimmy.degreesbatch.service.PerformanceService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.lang.Math.log
import java.net.URL
import kotlin.math.ln

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


    @Scheduled(cron = "*/10 * * * * *")
    private fun getPerformance() {
//       var result:List<PerformanceDto> = performanceService.selectPerformance()
        var apiUrl = "${KOPIS_PERFORMANCE_LIST}service=${KOPIS_APIKEY}&stdate=20220601&eddate=20220630&cpage=1&rows=5"

        var module = JacksonXmlModule()
        module.setDefaultUseWrapper(false)

        var om = XmlMapper(module).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        var url = URL(apiUrl)
        var resultResponse = om.readValue(url, ResultResponse::class.java)

        print(url)

        if (resultResponse.db != null) {
            for (i in resultResponse.db!!) {
                println(i)
            }
        }
        else {
            // todo :: 결과값 null인 경우 log 남기기
        }
    }
}