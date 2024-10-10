package com.example.rqchallenge.employees.model;

public class Mapper {

    public  Employee toDTO(EmployeeEntity employee) {
        return new Employee(employee.getEmployeeName(), employee.getSalary(), employee.getAge(), employee.getPictureUrl());
    }

    public  EmployeeEntity toEntity(Employee employee) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setName(employee.getEmployeeName());
        entity.setSalary(employee.getEmployeeSalary());
        entity.setAge(employee.getAge());
        entity.setPicture(employee.getPicture());

        return entity;
    }

}

