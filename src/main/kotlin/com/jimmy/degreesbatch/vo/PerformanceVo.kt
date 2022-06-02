package com.jimmy.degreesbatch.vo

import lombok.AccessLevel
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PerformanceVo {
    @Id
    private var mt20id: String = "";
    private var prfnm: String = "";
}