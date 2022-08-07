package com.jimmy.degreesbatch.service

import com.jimmy.degreesbatch.Mapper.ActorMapper
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
    lateinit var mapper: PerformanceMapper
    @Autowired
    lateinit var actorMapper: ActorMapper

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
            var actors = vo[i].prfcast.split(',')
            for (name in actors) {
                actorMapper.insertActor(vo[i].mt20id, name)
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