package pro.sky.collectionStart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.collectionStart.employeesService.Employee;
import pro.sky.collectionStart.exceptions.EmployeeAlreadyAddedException;
import pro.sky.collectionStart.exceptions.EmployeeNotFoundExceptions;
import pro.sky.collectionStart.exceptions.EmployeesStorageFullException;
import pro.sky.collectionStart.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam(value = "firstName") String firstName,
                                @RequestParam(value = "lastName") String lastName) {
        try {
            return employeeService.addEmployee(firstName, lastName);
        } catch (EmployeesStorageFullException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Больше нельзя добавлять сотрудников.", exception);
        } catch (EmployeeAlreadyAddedException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Сотрудник уже добавлен", exception);
        }
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam(value = "firstName") String firstName,
                                   @RequestParam(value = "lastName") String lastName) {
        try {
            return employeeService.removeEmployee(firstName, lastName);
        } catch (EmployeeNotFoundExceptions exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Такого сотрудника не существует.", exception);
        }
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam(value = "firstName") String firstName,
                                 @RequestParam(value = "lastName") String lastName) {
        try {
            return employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundExceptions exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Такого сотрудника не существует.", exception);
        }
    }

    @GetMapping("/print")
    public List<Employee> printAllEmployees(){
        return employeeService.printAllEmployees();
    }

}
//Объявить в контроллере 3 метода:
//        1. По адресу /employee/add?firstName=Ivan&lastName=Ivanov должен вернуться объект
//        Employee в формате JSON, т.е. { "firstName": "Ivan", "lastName": "Ivanov" },
//        или исключение ArrayIsFull в случае переполнения коллекции или EmployeeAlreadyAdded в
//        случае, когда сотрудник уже существует.
//        2. По адресу /employee/remove?firstName=Ivan&lastName=Ivanov должен вернуться объект
//        Employee в формате JSON, т.е. { "firstName": "Ivan", "lastName": "Ivanov" }, или
//        исключение со статусом EmployeeNotFound, если сотрудник отсутствует.
//3. По адресу /employee/find?firstName=Ivan&lastName=Ivanov должен вернуться объект Employee
// в формате JSON, т.е. { "firstName": "Ivan", "lastName": "Ivanov" }, или исключение
// со статусом EmployeeNotFound, если такой сотрудник отсутствует.