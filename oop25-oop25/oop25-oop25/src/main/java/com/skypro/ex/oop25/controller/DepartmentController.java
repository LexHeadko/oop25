package com.skypro.ex.oop25.controller;

import com.skypro.ex.oop25.model.Employee;
import com.skypro.ex.oop25.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/sum_salary")
    public double getEmployeeSalarySum(@RequestParam("departmentId") int department) {
        return departmentService.getEmployeeSalarySum(department);
    }
    @GetMapping("/max_salary")
    public double getEmployeeMaxSalary(@RequestParam("departmentId") int department) {
        return departmentService.getEmployeeMaxSalary(department);
    }
    @GetMapping("/min_salary")
    public double getEmployeeMinSalary(@RequestParam("departmentId") int department) {
        return departmentService.getEmployeeMinSalary(department);
    }
    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> getAll(@RequestParam("departmentId") int department) {
        return departmentService.getAll(department);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }
}
