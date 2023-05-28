package com.skypro.ex.oop25.service;

import com.skypro.ex.oop25.exception.EmployeeAlreadyAddedException;
import com.skypro.ex.oop25.exception.EmployeeNotFoundException;
import com.skypro.ex.oop25.exception.EmployeeStorageIsFullException;
import com.skypro.ex.oop25.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private static final int maxSize =5;

    public Employee add(String firstName, String lastName) {
        if (employees.size() >= maxSize) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employeeToAdd = new Employee(firstName, lastName);
        if (employees.contains(employeeToAdd)){
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employeeToAdd);
        return employeeToAdd;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employees.contains(employeeToRemove)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employeeToRemove);
        return employeeToRemove;
    }

    public Employee find(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (firstName.equalsIgnoreCase(employee.getFirstName())
                    && lastName.equalsIgnoreCase(employee.getLastName())) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }
    public List<Employee> getAll() {
        return Collections.unmodifiableList(employees);
    }
}
