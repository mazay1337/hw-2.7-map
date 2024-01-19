package proskyhw27.service;

import proskyhw27.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addsEmployee(String firstName, String lastName, int salary, int departmentId);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> findAll();
}