package com.skypro.ex.oop25.service;

import com.skypro.ex.oop25.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    public static final Collection<Employee> EMPLOYEES = Arrays.asList(
            new Employee("Aleksandr",
                    "Golovko",
                    1,
                    10000000),
            new Employee("Marina",
                    "bochkareva",
                    1,
                    1000000),
            new Employee("Sergey",
                    "Gerasimenko",
                    2,
                    100000),
            new Employee("maksim",
                    "borunov",
                    2,
                    50000),
            new Employee("Nikolai",
                    "nogin",
                    3,
                    10000)
    );
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void init() {
        Mockito.when(employeeService.getAll()).thenReturn(EMPLOYEES);
    }

    @Test
    void sum() {
        double actual = departmentService.getEmployeeSalarySum(1);
        assertEquals(11000000, actual);
    }

    @Test
    void max() {
        double actual = departmentService.getEmployeeMaxSalary(1);
        assertEquals(10000000, actual);
    }

    @Test
    void min() {
        double actual = departmentService.getEmployeeMinSalary(1);
        assertEquals(1000000, actual);
    }

    @Test
    void getAllByDepartment() {
        List<Employee> actual = departmentService.getAll(3);
        Collection<Employee> excpected = Collections.singletonList(
                new Employee("Nikolai",
                        "nogin",
                        3,
                        10000));
        assertIterableEquals(excpected, actual);
    }
    @Test
    void getAll() {
        Map<Integer, List<Employee>> actual = departmentService.getAll();
        Employee nikolai = new Employee("Nikolai",
                "nogin",
                3,
                10000);
        assertTrue(actual.get(3).contains(nikolai));
        assertFalse(actual.get(2).contains(nikolai));
    }
}
