package com.mindex.challenge.data;

import lombok.*;

import java.time.Instant;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Compensation {
    private Employee employee;
    private Integer salary;
    private Instant effectiveDate;
}
