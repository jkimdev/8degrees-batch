package com.jimmy.degreesbatch.service

import com.jimmy.degreesbatch.Mapper.CrewMapper
import com.jimmy.degreesbatch.Mapper.PriceMapper
import com.jimmy.degreesbatch.Mapper.ProducerMapper
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ProducerService {
    @Autowired
    lateinit var mapper: ProducerMapper

    fun insertProducer(performanceId: String, Producer: String) {
        return mapper.insertProducer(performanceId, Producer)
    }
    fun deleteProducer() {
        return mapper.deleteProducer()
    }
}