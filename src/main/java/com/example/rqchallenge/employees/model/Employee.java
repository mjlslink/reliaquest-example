package com.example.rqchallenge.employees.model;

/**
 * This is a DTO object, as described by Martin Fowler, Patterns of
 * Enterprise Application Architecture. It has no logic and actually should
 * only return the data necessary for the UI to function, rather than the
 * whole data structure.
 *
 * This is a very simple data structure, a more robust database record may contain authentication
 * information or authorization roles and other information not intended to be seen.
 *
 * I have not renamed it to EMployeeDTO in order to retain the original class names
 * in the (predefined) interface.
 *
 * @author  Michael Larsen
 */
public class Employee {
    private String employeeName;
    private Integer yearlySalary;
    private Integer age;
    private String picture;

    public Employee(String employeeName, Integer salary, Integer age, String picture) {
        this.employeeName = employeeName;
        this.yearlySalary = salary;
        this.age = age;
        this.picture = picture;
    }

    public void setEmployeeName(String name) {
        this.employeeName = name;
    }

    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeSalary(Integer yearlysalary) {
        this.yearlySalary =yearlysalary;
    }

    public Integer getEmployeeSalary() {
        return yearlySalary;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
