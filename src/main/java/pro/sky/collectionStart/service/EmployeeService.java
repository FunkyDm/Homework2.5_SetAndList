package pro.sky.collectionStart.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionStart.employeesService.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private static final int MAX_NUM_EMPLOYEES = 10;

    private final List<Employee> employees = new ArrayList<>(MAX_NUM_EMPLOYEES);

    public void addEmployee(String firstName, String lastName){
        for(int i = 0; i < employees.size(); i++){
            if(employees.get(i) == null){
                employees.add(new Employee(firstName, lastName));
            }
        }
    }
}

//Реализовать в сервисе три метода, которые принимают в качестве параметров firstName
// и lastName:
//        1. Добавить сотрудника.
//        2. Удалить сотрудника.
//        3. Найти сотрудника.