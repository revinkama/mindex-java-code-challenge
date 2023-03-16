package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Service
public class ReportingStructureServiceImpl implements ReportStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure getReportStructure(String id) {
        LOG.debug("Reading report structure for employee id: " + id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportingStructure reportingStructure = new ReportingStructure(new ArrayList<>(), 0);
        for (Employee employee1: employee.getDirectReports()) {
            reportingStructure.getEmployees().add(employee1);
            if (!Objects.isNull(employee1.getDirectReports())){
                employee1.getDirectReports().forEach(employee2 -> reportingStructure.getEmployees().add(employee2));
            }
        }
        reportingStructure.setNumberOfReports(reportingStructure.getEmployees().size());
        return reportingStructure;

    }
}
