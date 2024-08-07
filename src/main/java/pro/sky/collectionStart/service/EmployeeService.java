package pro.sky.collectionStart.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionStart.employeesService.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    private static final int MAX_NUM_EMPLOYEES = 10;

    private final List<Employee> employees = new ArrayList<>(MAX_NUM_EMPLOYEES);

    public Employee addEmployee(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        employees.add(employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName){
        for(Employee employee : employees){
            if(Objects.equals(employee.getFirstName(), firstName) && Objects.equals(employee.getLastName(), lastName)){
                employees.remove(employee);
                return employee;
            }
        }
        throw new RuntimeException();
    }

    public Employee findEmployee(String firstName, String lastName){
        for(Employee employee : employees){
            if(Objects.equals(employee.getFirstName(), firstName) && Objects.equals(employee.getLastName(), lastName)){
                System.out.println(employee);
                return employee;
            }
        }
        throw new RuntimeException();
    }
}