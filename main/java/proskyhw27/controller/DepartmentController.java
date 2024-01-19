package proskyhw27.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proskyhw27.model.Employee;
import proskyhw27.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public Employee findEmployeeWithMaxSalaryByDepartmentId(@RequestParam int departmentId) {
        return departmentService.findEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee findEmployeeWithMinSalaryByDepartmentId(@RequestParam int departmentId) {
        return departmentService.findEmployeeWithMinSalary(departmentId);
    }

    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> findEmployees() {
        return departmentService.findEmployeesByDepartmentSortedByNameSurName();
    }

    @GetMapping(path = "/all", params = {"departmentId"})
    public Collection<Employee> findEmployees(@RequestParam int departmentId) {
        return departmentService.findEmployeesByDepartmentSortedByNameSurName(departmentId);
    }
}