package com.jimmy.degreesbatch.service

import com.jimmy.degreesbatch.Mapper.ActorMapper
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ActorService {
    @Autowired
    lateinit var mapper: ActorMapper

    fun insertActor(performanceId: String, name: String) {
        return mapper.insertActor(performanceId, name)
    }

    fun deleteActor() {
        return mapper.deleteActor()
    }
}