package com.example.rqchallenge.employees;

import com.example.rqchallenge.employees.controller.EmployeeControllerImpl;
import com.example.rqchallenge.employees.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * This test is annotated as a SpringBootTest in order to allow it to start the server
 * before executing the endpoint tests,
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeEntityControllerImplTest {

    @InjectMocks
    private EmployeeControllerImpl employeeController;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private Employee employee;

    @Test
    public void getAllEmployees() throws IOException, InterruptedException {

        //given
        when(employeeService.getEmployees()).thenReturn(Arrays.asList(employee));
        when(employee.getEmployeeName()).thenReturn("Larsen");
/*

        HttpRequest request = HttpRequest
                .newBuilder(URI.create("http://localhost:3000/employees"))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> repsonse = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString());

*/
        ResponseEntity<List<Employee>> allEmployees = employeeController.getAllEmployees();

        assertThat(allEmployees.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(allEmployees.getBody().get(0).getEmployeeName()).isEqualTo("Larsen");

    }


    @Test
    public void createEmployee_no_pic() {
        //given
        Map<String, Object> testInout = new HashMap<>();
        testInout.put("employee_name", "Larsen");
        testInout.put("employee_salary",145000);
        testInout.put("employee_age", 45);

        ResponseEntity<Employee> employeeResponse = employeeController.createEmployee(testInout);

        assertThat(employeeResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(employeeResponse.getBody().getEmployeeName()).isEqualTo("Larsen");
    }

    @Test
    public void createEmployee_duplicate() {

        //given
        Map<String, Object> testInout = new HashMap<>();
        testInout.put("employee_name", "Larsen");
        testInout.put("employee_salary",145000);
        testInout.put("employee_age", 45);

        ResponseEntity<Employee> employee1 = employeeController.createEmployee(testInout);
        assertThat(employee1.getStatusCode()).isEqualTo(HttpStatus.IM_USED);
    }


    @Test
    public void createEmployee_complete() {
        //given
        Map<String, Object> testInout = new HashMap<>();
        testInout.put("employee_name", "Larsen");
        testInout.put("employee_salary",145000);
        testInout.put("employee_age", 45);
        testInout.put("picture", "");

        ResponseEntity<Employee> employee = employeeController.createEmployee(testInout);

        assertThat(employee.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(employee.getBody().getEmployeeName()).isEqualTo("Larsen");
    }



    @Test
    public void getEmployeesByNameSearch_defined_name() {

        when(employeeService.getEmployeesByName("Larsen")).thenReturn(List.of(employee));

        ResponseEntity<List<Employee>> employee = employeeController.getEmployeesByNameSearch("Michael Larsen");

        assertThat(employee.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getEmployeesByNameSearch_undefined_name() {
        when(employeeService.getEmployeesByName("")).thenReturn(List.of());

        ResponseEntity<List<Employee>> employeesByNameSearch = employeeController.getEmployeesByNameSearch("");

        assertThat(employeesByNameSearch.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(employeesByNameSearch.getBody().size()).isEqualTo(0);

    }

    @Test
    public void getEmployeeById_defined_id() {

        ResponseEntity<Optional<Employee>> employeeById = employeeController.getEmployeeById("5");

        assertThat(employeeById.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(employeeById.getBody().get().getEmployeeName()).isEqualTo("Martin");

    }

    @Test
    public void getEmployeeById_undefined_id() {

        ResponseEntity<Optional<Employee>> employeeById = employeeController.getEmployeeById("1000");

        assertThat(employeeById.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void getHighestSalaryOfEmployees_employee_exists() {

        ResponseEntity<Integer> highestSalaryOfEmployees = employeeController.getHighestSalaryOfEmployees();

        assertThat(highestSalaryOfEmployees.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(highestSalaryOfEmployees.getBody().intValue()).isEqualTo(	450000);


    }

    @Test
    public void getTopTenHighestEarningEmployeeNames() {

        ResponseEntity<List<String>> topTenHighestEarningEmployeeNames = employeeController.getTopTenHighestEarningEmployeeNames();

        assertThat(topTenHighestEarningEmployeeNames.getStatusCode()).isEqualTo(HttpStatus.OK);
        //TODO more checks
    }

}