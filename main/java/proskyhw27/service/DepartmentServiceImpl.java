package proskyhw27.service;

import org.springframework.stereotype.Service;
import proskyhw27.exception.EmployeeNotFoundException;
import proskyhw27.model.Employee;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findEmployeeWithMaxSalary(int departmentId) {
        return employeeService
                .findAll()
                .stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee findEmployeeWithMinSalary(int departmentId) {
        return employeeService
                .findAll()
                .stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Map<Integer, List<Employee>> findEmployeesByDepartmentSortedByNameSurName() {
        return employeeService
                .findAll()
                .stream()
                .sorted(comparing(Employee::getLastName)
                        .thenComparing(Employee::getFirstName))
                .collect(groupingBy(Employee::getDepartmentId));
    }


    @Override
    public Collection<Employee> findEmployeesByDepartmentSortedByNameSurName(int departmentId) {
        return employeeService
                .findAll()
                .stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .sorted(comparing(Employee::getLastName)
                        .thenComparing(Employee::getFirstName))
                .collect(toList());
    }

    public Map<String, Double> userGroupByTest() {
        return employeeService
                .findAll()
                .stream()
                .collect(groupingBy(Employee::getLastName, Collectors.averagingInt(Employee::getSalary)));
    }
}
