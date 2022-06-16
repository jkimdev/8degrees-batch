package com.jimmy.degreesbatch.service

import com.jimmy.degreesbatch.Mapper.PerformanceMapper
import com.jimmy.degreesbatch.Model.PerformanceDto
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class PerformanceService {

 @Autowired
    lateinit var mapper: PerformanceMapper;

    fun selectPerformance(): List<PerformanceDto> {
        return mapper.findById();
    }

    fun insertPerformance(vo: PerformanceDto) {
        mapper.insertPerformance(vo)
    }
}