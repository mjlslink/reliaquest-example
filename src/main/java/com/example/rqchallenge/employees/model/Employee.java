package com.example.rqchallenge.employees.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
@AllArgsConstructor
@Builder
public class Employee {
    @Getter @Setter
    private String employeeName;
    @Getter @Setter
    private Integer employeeSalary;
    @Getter @Setter
    private Integer age;
    @Getter @Setter
    private String picture;
}
