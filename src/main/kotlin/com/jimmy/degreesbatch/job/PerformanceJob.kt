package com.jimmy.degreesbatch.job

import com.jimmy.degreesbatch.Model.PerformanceDto
import com.jimmy.degreesbatch.service.PerformanceService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@Slf4j
class PerformanceJob(performanceService: PerformanceService) {

   private var performanceService: PerformanceService = PerformanceService();

    init {
        this.performanceService = performanceService
    }

    @Value("\${kopis.apikey}") val KOPIS_APIKEY: String = "ebcfbb27f8a848538981849b321a58aa";
    @Value("\${kopis.performance.list}") val KOPIS_PERFORMANCE_LIST: String? = null


    @Scheduled(cron = "*/10 * * * * *")
    private fun getPerformance() {
       var result:List<PerformanceDto> = performanceService.selectPerformance()

        println(result)
    }
}