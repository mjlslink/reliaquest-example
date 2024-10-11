package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.EmployeeService;
import com.example.rqchallenge.employees.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeControllerImpl  {

    private final EmployeeService employeeeService;

    @Autowired
    EmployeeControllerImpl(final EmployeeService employeeService) {
        this.employeeeService = employeeService;
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
        return new ResponseEntity<List<Employee>> (employeeeService.getEmployees(), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Map<String, Object> employeeInput) {

        // these parameters are set from the UI (e.g. an HTML form or Postman) and do
        // not correspond to the database column names - the sample data seems to
        // indicate these different names

        System.out.println(employeeInput);

        //check the input
        Employee newEmployee = new Employee(
                employeeInput.get("employee_name").toString(),
                (Integer)employeeInput.get("employee_salary"),
                (Integer)employeeInput.get("employee_age"),
                employeeInput.get("picture").toString());

        Employee employee = employeeeService.saveEmployee(newEmployee);

        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @GetMapping("/search/{searchString}")
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable("searchString") String searchString) {
        System.out.println("searching for name = " + searchString);
        return new ResponseEntity<List<Employee>>(employeeeService.getEmployeesByName(searchString), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable("id") String id) {
        System.out.println("searching for id = " + id);
        return new ResponseEntity<Optional<Employee>>(employeeeService.getEmployeeById(id), HttpStatus.OK);
    }


    @GetMapping("/highestSalary")
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        return new ResponseEntity<Integer>(employeeeService.getHighestSalaryOfEmployees(), HttpStatus.OK);
    }

        @GetMapping("/topTenHighestEarningEmployeeNames")
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        return new ResponseEntity<List<String>>(employeeeService.getHighestEarningEmployeeNames(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") String id) {
        return new ResponseEntity<String>(employeeeService.deleteEmployeeById(id), HttpStatus.OK);
    }
}
