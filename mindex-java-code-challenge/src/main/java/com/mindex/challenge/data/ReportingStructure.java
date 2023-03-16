package com.mindex.challenge.data;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ReportingStructure {
    private List<Employee> employees;
    private Integer numberOfReports;

}
