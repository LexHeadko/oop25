package com.skypro.ex.oop25.service;

import com.skypro.ex.oop25.exception.EmployeeAlreadyAddedException;
import com.skypro.ex.oop25.exception.EmployeeStorageIsFullException;
import com.skypro.ex.oop25.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @Test
    void whenFullThenThrowExceptionJ() {
        for (int i = 0; i < 5; i++) {
            char additionChar = (char) ('a' + i);
            Employee employee = new Employee(
                    "Name" + additionChar,
                    "Second_name",
                    1,
                    10000);
            employeeService.add(employee);
        }
        assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.add(new Employee(
                "asd",
                "dsa",
                1,
                11
        )));
    }
    @Test
    void whenNotUniqueThenThrowException() {
        Employee employee = new Employee(
                "Aleksandr",
                "Golovko",
                1,
                10000000);
        employeeService.add(employee);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add(employee));
    }

    @Test
    void addPositive() {
        Employee employee = new Employee(
                "Aleksandr",
                "Golovko",
                1,
                10000000);
        employeeService.add(employee);
        assertTrue(employeeService.getAll().contains(employee));
    }
}