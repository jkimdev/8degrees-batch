package com.jimmy.degreesbatch.service

import com.jimmy.degreesbatch.Mapper.CrewMapper
import com.jimmy.degreesbatch.Mapper.PriceMapper
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class PriceService {
    @Autowired
    lateinit var mapper: PriceMapper

    fun insertPrice(performanceId: String, price: String) {
        return mapper.insertPrice(performanceId, price)
    }
    fun deletePrice() {
        return mapper.deletePrice()
    }
}