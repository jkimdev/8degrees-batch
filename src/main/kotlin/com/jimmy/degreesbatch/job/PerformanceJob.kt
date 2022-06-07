package com.jimmy.degreesbatch.job

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.jimmy.degreesbatch.Model.DBS
import com.jimmy.degreesbatch.Model.PerformanceDto
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

    @Value("\${kopis.apikey}") val KOPIS_APIKEY: String = "ebcfbb27f8a848538981849b321a58aa";
    @Value("\${kopis.performance.list}") val KOPIS_PERFORMANCE_LIST: String? = "http://www.kopis.or.kr/openApi/restful/pblprfr?"


    @Scheduled(cron = "*/10 * * * * *")
    private fun getPerformance() {
//       var result:List<PerformanceDto> = performanceService.selectPerformance()
        var apiUrl = "${KOPIS_PERFORMANCE_LIST}service=${KOPIS_APIKEY}&stdate=20160601&eddate=20160630&cpage=1&rows=5"
        var om = XmlMapper().configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var url = URL(apiUrl)
        var resultResponse: ResultResponse<PerformanceDto> = om.readValue(url, ResultResponse::class.java) as ResultResponse<PerformanceDto>

        println(url)
        println(resultResponse.response?.header)
    }
}