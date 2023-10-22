package com.jimmy.degreesbatch.service

import com.jimmy.degreesbatch.Mapper.CrewMapper
import com.jimmy.degreesbatch.Mapper.DetailMapper
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class DetailService {
    @Autowired
    lateinit var mapper: DetailMapper

    fun insertDetail(performanceId: String, url: String) {
        return mapper.insertDetail(performanceId, url)
    }
    fun deleteDetail() {
        return mapper.deleteDetail()
    }
}