package com.skypro.ex.oop25.service;

import com.skypro.ex.oop25.exception.EmployeeAlreadyAddedException;
import com.skypro.ex.oop25.exception.EmployeeNotFoundException;
import com.skypro.ex.oop25.exception.EmployeeStorageIsFullException;
import com.skypro.ex.oop25.exception.InvalidDataException;
import com.skypro.ex.oop25.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private static final int maxSize = 5;
    private final Map<String, Employee> employees = new HashMap<>(maxSize);

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee add(Employee employee) {
        if (!StringUtils.isAlpha(employee.getFirstName())
                || !StringUtils.isAlpha(employee.getLastName())) {
            throw new InvalidDataException();
        }
        if (employees.size() >= maxSize) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(createKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        correctCase(employee);
        employees.put(createKey(employee), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        return employees.remove(createKey(firstName, lastName));
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(createKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    private static String createKey(Employee employee) {
        return createKey(employee.getFirstName(), employee.getLastName());
    }
    private static String createKey(String firstName, String lastName) {
        String key = firstName + lastName;
        return key.toLowerCase();
    }

    private static void correctCase(Employee employee) {
        String correctedFirstName = StringUtils.capitalize(employee.getFirstName().toLowerCase());
        employee.setFirstName(correctedFirstName);
        String correctedLastName = StringUtils.capitalize(employee.getLastName().toLowerCase());
        employee.setLastName(correctedLastName);
    }
}
