package com.example.rqchallenge.employees;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.model.EmployeeEntity;
import com.example.rqchallenge.employees.model.Mapper;
import com.sun.istack.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final Logger logger = LogManager.getLogger();

    private final EmployeeRepository repository;
    private final Mapper mapper;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
        mapper = new Mapper();
    }

    public Employee saveEmployee(Employee employee){
        EmployeeEntity savedEmployee = repository.saveAndFlush(mapper.toEntity(employee));
        return  mapper.toDTO(savedEmployee);
    }

    public List<Employee> getEmployees() {

        logger.info("Getting all employees" );

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<String> getHighestEarningEmployeeNames(){
        List<Employee> employees = getEmployees();

        Integer maxSalary = employees.stream()
                .mapToInt(Employee::getEmployeeSalary)
                .max()
                .orElseThrow();

        logger.info("Max salary is {}", maxSalary);

        return employees.stream()
                .filter(e -> e.getEmployeeSalary() < maxSalary)
                .limit(10)
                .map(Employee::getEmployeeName)
                .collect(Collectors.toList());
    }

    public Optional<Employee> getEmployeeById(@NotNull String id){
        return Optional.of(mapper.toDTO(repository.getById(Integer.valueOf(id))));
    }

    public List<Employee> getEmployeesByName(@NotNull String name){

        logger.info("Getting employee information for {}", name);

        return repository.findByName(name).stream()
                .filter(employee -> employee.getName().contains(name))
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }


    public Integer getHighestSalaryOfEmployees(){
        List<Employee> employees = getEmployees();

        logger.info("Getting highest salary of all employees");

        return employees.stream()
                .mapToInt(Employee::getEmployeeSalary)
                .max()
                .orElseThrow();
    }

    public String deleteEmployeeById(String id) {
        EmployeeEntity byId = repository.getById(Integer.parseInt(id));
        String name = byId.getName();

        logger.info("Deleting {}", name);

        repository.delete(byId);

        return name;
    }
}

