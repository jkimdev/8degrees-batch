package com.jimmy.degreesbatch.repository

import com.jimmy.degreesbatch.vo.PerformanceVo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
public interface PerformanceRepository: JpaRepository<PerformanceVo, String> {

    public override fun findById(id: String): Optional<PerformanceVo>

}