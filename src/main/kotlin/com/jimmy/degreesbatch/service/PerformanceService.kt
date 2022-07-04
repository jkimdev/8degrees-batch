package com.jimmy.degreesbatch.service

import com.jimmy.degreesbatch.Mapper.PerformanceMapper
import com.jimmy.degreesbatch.Model.PerformanceDetailDto
import com.jimmy.degreesbatch.Model.PerformanceDto
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class PerformanceService {

 @Autowired
    lateinit var mapper: PerformanceMapper;

    // SELECT
    fun selectPerformance(): List<PerformanceDto> {
        return mapper.findById();
    }

    // INSERT
    fun insertPerformance(vo: List<PerformanceDetailDto>) {

        mapper.deletePerformance()
        for (i in vo.indices) {
            mapper.insertPerformance(vo[i])
        }
    }
}