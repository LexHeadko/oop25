package com.skypro.ex.oop25.service;

import com.skypro.ex.oop25.exception.EmployeeAlreadyAddedException;
import com.skypro.ex.oop25.exception.EmployeeNotFoundException;
import com.skypro.ex.oop25.exception.EmployeeStorageIsFullException;
import com.skypro.ex.oop25.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();
    private static final int maxSize = 5;

    public Employee add(String firstName, String lastName) {
        if (employees.size() >= maxSize) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employeeToAdd = new Employee(firstName, lastName);
        if (employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(firstName, lastName), employeeToAdd);
        return employeeToAdd;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(createKey(firstName, lastName));
    }

    public Employee find(String firstName, String lastName) {
        if (!employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(createKey(firstName, lastName));
    }

    public List<Employee> getAll() {
        return List.copyOf(employees.values());
    }

    private String createKey(String firstName, String lastName) {
        String key = firstName + lastName;
        return key.toLowerCase();
    }

}
