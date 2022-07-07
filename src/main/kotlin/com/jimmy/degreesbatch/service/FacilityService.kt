package com.jimmy.degreesbatch.service

import com.jimmy.degreesbatch.Mapper.FacilityMapper
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class FacilityService {

    @Autowired
    lateinit var mapper: FacilityMapper
}