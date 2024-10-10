package com.example.rqchallenge.employees;

import com.example.rqchallenge.employees.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    public List<EmployeeEntity> findByName(String name);
}
