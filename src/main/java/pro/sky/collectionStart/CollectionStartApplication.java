package pro.sky.collectionStart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.sky.collectionStart.employeesService.Employee;
import pro.sky.collectionStart.service.EmployeeService;

@SpringBootApplication
public class CollectionStartApplication {

	public static void main(String[] args) {

		SpringApplication.run(CollectionStartApplication.class, args);
	}

}
