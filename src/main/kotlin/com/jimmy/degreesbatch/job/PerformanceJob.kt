package com.jimmy.degreesbatch.job

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.jimmy.degreesbatch.Utility.*
import com.jimmy.degreesbatch.response.BoxOfficeResponse
import com.jimmy.degreesbatch.response.DetailPerformanceResultResponse
import com.jimmy.degreesbatch.response.FacilityResponse
import com.jimmy.degreesbatch.response.PerformanceResultResponse
import com.jimmy.degreesbatch.service.*
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.net.URL

@Component
@Slf4j
class PerformanceJob(
    performanceService: PerformanceService,
    detailService: DetailService,
    facilityService: FacilityService,
    boxOfficeService: BoxOfficeService,
    actorService: ActorService,
    crewService: CrewService,
    priceService: PriceService,
    producerService: ProducerService,
    scheduleService: ScheduleService,
) {


    private var performanceService: PerformanceService = PerformanceService()
    private var detailService: DetailService = DetailService()
    private var facilityService: FacilityService = FacilityService()
    private var boxOfficeService: BoxOfficeService = BoxOfficeService()
    private var actorService: ActorService = ActorService()
    private var crewService: CrewService = CrewService()
    private var priceService: PriceService = PriceService()
    private var producerService: ProducerService = ProducerService()
    private var scheduleService: ScheduleService = ScheduleService()

    init {
        this.performanceService = performanceService
        this.detailService = detailService
        this.facilityService = facilityService
        this.boxOfficeService = boxOfficeService
        this.actorService = actorService
        this.crewService = crewService
        this.priceService = priceService
        this.producerService = producerService
        this.scheduleService = scheduleService
    }

    @Value("\${kopis.apikey}")
    lateinit var KOPIS_APIKEY: String;

    @Value("\${kopis.performance}")
    lateinit var KOPIS_PERFORMANCE: String

    @Value("\${kopis.performance.detail}")
    lateinit var KOPIS_PERFORMANCE_DETAIL: String

    @Value("\${kopis.facility}")
    lateinit var KOPIS_FACILITY: String

    @Value("\${kopis.boxOffice}")
    lateinit var KOPIS_BOXOFFICE: String


//        @Scheduled(cron = "0 0 6,19 * * *")
    @Scheduled(cron="0/50 * * * * *")
    private fun getPerformance() {

        performanceService.deletePerformance()
        detailService.deleteDetail()
        actorService.deleteActor()
        crewService.deleteCrew()
        priceService.deletePrice()
        producerService.deleteProducer()
        scheduleService.deleteProducer()

        var performanceAPIUrl =
            "${KOPIS_PERFORMANCE}service=${KOPIS_APIKEY}&stdate=${getCurrentTime().withoutDash()}&eddate=${getCurrentTime().after3Months().withoutDash()}&cpage=1&rows=10000"
        var xmlModule = JacksonXmlModule()
        xmlModule.setDefaultUseWrapper(false)
        var xmlConfigure = XmlMapper(xmlModule).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        var performanceURL = URL(performanceAPIUrl)
        var performanceResultResponse = xmlConfigure.readValue(performanceURL, PerformanceResultResponse::class.java)
        if (performanceResultResponse != null) {
            for (i in performanceResultResponse.db?.indices!!) {
                var performanceDetailAPIUrl =
                    KOPIS_PERFORMANCE_DETAIL + performanceResultResponse.db!![i].mt20id + "/?service=${KOPIS_APIKEY}"
                var performanceDetailURL = URL(performanceDetailAPIUrl)
                var performanceDetailResultResponse =
                    xmlConfigure.readValue(performanceDetailURL, DetailPerformanceResultResponse::class.java)
                performanceDetailResultResponse.db?.let { performanceService.insertPerformance(it) }
            }
        }
        println("performance done")
        getFacility()
        getBoxOffice()
    }

    //    @Scheduled(cron = "*/55 * * * * *")
    private fun getBoxOffice() {

        boxOfficeService.deleteBoxOffice()

        val boxOfficeAPIUrl = "${KOPIS_BOXOFFICE}service=${KOPIS_APIKEY}&ststype=day&date=${getCurrentTime().yesterDay().withoutDash()}"
        val xmlModule = JacksonXmlModule()
        xmlModule.setDefaultUseWrapper(false)
        val xmlConfigure = XmlMapper(xmlModule).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val boxOfficeURL = URL(boxOfficeAPIUrl)
        val boxOfficeResultResponse = xmlConfigure.readValue(boxOfficeURL, BoxOfficeResponse::class.java)

        if (boxOfficeResultResponse != null) {
            for (i in boxOfficeResultResponse.boxof?.indices!!) {
                boxOfficeResultResponse.boxof!![i].let { boxOfficeService.insertBoxOffice(it) }
            }
        }
        println("boxofiice done")
    }

    //    @Scheduled(cron = "*/55 * * * * *")
    private fun getFacility() {

//        facilityService.deleteFacility()

        val facilities: List<String> = performanceService.selectDistinctFC()
        val xmlModule = JacksonXmlModule()
        xmlModule.setDefaultUseWrapper(false)
        val xmlConfigure = XmlMapper(xmlModule).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        for (i in facilities.indices) {
            val facilityAPIUrl = "${KOPIS_FACILITY}${facilities[i]}?service=${KOPIS_APIKEY}"
            val url = URL(facilityAPIUrl)
            val facilityResultResponse = xmlConfigure.readValue(url, FacilityResponse::class.java)
            if (facilityResultResponse != null) {
                facilityResultResponse.db?.let { facilityService.insertFacility(it) }
            }
        }
        println("facility done")
    }
}
