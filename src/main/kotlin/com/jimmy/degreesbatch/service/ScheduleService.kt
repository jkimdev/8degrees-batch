package com.jimmy.degreesbatch.service

import com.jimmy.degreesbatch.Mapper.CrewMapper
import com.jimmy.degreesbatch.Mapper.PriceMapper
import com.jimmy.degreesbatch.Mapper.ProducerMapper
import com.jimmy.degreesbatch.Mapper.ScheduleMapper
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ScheduleService {
    @Autowired
    lateinit var mapper: ScheduleMapper

    fun insertProducer(performanceId: String, schedule: String) {
        return mapper.insertSchedule(performanceId, schedule)
    }
    fun deleteProducer() {
        return mapper.deleteSchedule()
    }
}