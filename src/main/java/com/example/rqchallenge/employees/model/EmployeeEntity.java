package com.example.rqchallenge.employees.model;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer salary;

    @Column(nullable = false)
    private Integer age;

    @Column
    private String picture;

    public String getEmployeeName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPictureUrl() {
        return picture;
    }

    public void setPicture(String pictureUrl) {
        this.picture = pictureUrl;
    }

    public Integer getId() {
        return id;
    }
}
