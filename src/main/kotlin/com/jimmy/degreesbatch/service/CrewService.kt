package com.jimmy.degreesbatch.service

import com.jimmy.degreesbatch.Mapper.CrewMapper
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CrewService {
    @Autowired
    lateinit var mapper: CrewMapper

    fun insertCrew(performanceId: String, crew: String) {
        return mapper.insertCrew(performanceId, crew)
    }
    fun deleteCrew() {
        return mapper.deleteCrew()
    }
}