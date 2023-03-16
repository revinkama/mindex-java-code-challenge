package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String structureUrl;
    @Autowired
    private ReportStructureService reportStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        structureUrl = "http://localhost:" + port + "/{id}/directReports";
    }

    @Test
    public void testReadStructure() {

        Employee testEmployee1 = new Employee();
        testEmployee1.setEmployeeId("123455");
        testEmployee1.setFirstName("John");
        testEmployee1.setLastName("Doe");
        testEmployee1.setDepartment("Engineering");
        testEmployee1.setPosition("Developer");

        Employee testEmployee2 = new Employee();
        testEmployee2.setEmployeeId("54312");
        testEmployee2.setFirstName("Jane");
        testEmployee2.setLastName("Doe");
        testEmployee2.setDepartment("Sales");
        testEmployee2.setPosition("Developer");

        Employee testEmployee3 = new Employee();
        testEmployee3.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        testEmployee3.setFirstName("Mike");
        testEmployee3.setLastName("Dane");
        testEmployee3.setDepartment("Engineering");
        testEmployee3.setPosition("Manager");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(testEmployee1);
        employeeList.add(testEmployee2);

        testEmployee3.setDirectReports(employeeList);

        ReportingStructure readStructure = restTemplate.getForEntity(structureUrl, ReportingStructure.class, testEmployee3.getEmployeeId()).getBody();
        assertNotNull(readStructure.getEmployees());
        assertEquals(Optional.ofNullable(readStructure.getNumberOfReports()), Optional.ofNullable(2));

    }
}
