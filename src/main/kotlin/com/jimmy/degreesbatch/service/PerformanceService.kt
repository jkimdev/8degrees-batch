package com.jimmy.degreesbatch.service

import com.jimmy.degreesbatch.Mapper.*
import com.jimmy.degreesbatch.Model.DetailDTO
import com.jimmy.degreesbatch.Model.PerformanceDetailDto
import com.jimmy.degreesbatch.Model.PerformanceDto
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class PerformanceService {
    @Autowired
    lateinit var mapper: PerformanceMapper
    @Autowired
    lateinit var detailMapper: DetailMapper
    @Autowired
    lateinit var actorMapper: ActorMapper
    @Autowired
    lateinit var crewMapper: CrewMapper
    @Autowired
    lateinit var priceMapper: PriceMapper
    @Autowired
    lateinit var producerMapper: ProducerMapper
    @Autowired
    lateinit var scheduleMapper: ScheduleMapper

    // SELECT
    fun selectPerformance(): List<PerformanceDto> {
        return mapper.findById();
    }

    fun selectDistinctFC(): List<String> {
        return mapper.selectDistinctFC()
    }

    // INSERT
    fun insertPerformance(vo: List<PerformanceDetailDto>) {
        for (i in vo.indices) {
            val actors = vo[i].prfcast.split(',')
            val crews = vo[i].prfcrew.split(',')
            val prices = vo[i].pcseguidance.split(", ")
            val producers = vo[i].entrpsnm.split(',')
            val schedules = vo[i].dtguidance.split(", ")
            val styurls = vo[i].styurls.map { t -> t.styurl }

            for (name in actors) {
                actorMapper.insertActor(vo[i].mt20id, name)
            }
            for (crew in crews) {
                crewMapper.insertCrew(vo[i].mt20id, crew)
            }
            for (price in prices) {
                priceMapper.insertPrice(vo[i].mt20id, price)
            }
            for (producer in producers) {
                producerMapper.insertProducer(vo[i].mt20id, producer)
            }
            for (schedule in schedules) {
                scheduleMapper.insertSchedule(vo[i].mt20id, schedule)
            }
            for (url in styurls) {
                if (url.isNotEmpty()) {
                    detailMapper.insertDetail(vo[i].mt20id, url)
                }
            }

            mapper.insertPerformance(vo[i])
        }
    }

    // DELETE
    fun deletePerformance() {
        mapper.deletePerformance()
    }
//    fun deleteActor() {
//        mapper.deleteActor()
//    }
}