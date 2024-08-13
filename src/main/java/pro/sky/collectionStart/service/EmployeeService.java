package pro.sky.collectionStart.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionStart.employeesService.Employee;
import pro.sky.collectionStart.exceptions.EmployeeAlreadyAddedException;
import pro.sky.collectionStart.exceptions.EmployeeNotFoundExceptions;
import pro.sky.collectionStart.exceptions.EmployeesStorageFullException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    private static final int MAX_NUM_EMPLOYEES = 10;

    private final List<Employee> employees = new ArrayList<>(MAX_NUM_EMPLOYEES);

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() == MAX_NUM_EMPLOYEES) {
            throw new EmployeesStorageFullException("Больше нельзя добавлять сотрудников.");
        } else if (employees.contains(new Employee(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует.");
        } else {
            Employee employee = new Employee(firstName, lastName);
            employees.add(employee);
            return employee;
        }
    }

    public Employee removeEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (Objects.equals(employee.getFirstName(), firstName) && Objects.equals(employee.getLastName(), lastName)) {
                employees.remove(employee);
                return employee;
            }
        }
        throw new EmployeeNotFoundExceptions("Такого сотрудника не существует.");
    }

    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (Objects.equals(employee.getFirstName(), firstName) && Objects.equals(employee.getLastName(), lastName)) {
                System.out.println(employee);
                return employee;
            }
        }
        throw new EmployeeNotFoundExceptions("Такого сотрудника не существует.");
    }

    public List<Employee> printAllEmployees(){
        return employees;
    }

}