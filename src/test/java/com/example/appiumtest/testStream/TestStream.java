package com.example.appiumtest.testStream;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testng.annotations.Test;
import java.util.*;
import java.util.stream.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestStream  {
    @Test(priority = 1,groups="smoke15")
    static void evaluateTax(){
            DataBase.getEmployees().forEach(e->System.out.println(e.toString()));
    }
    @Test
    static void evaluateTax2(){
        List<Employee> employees= DataBase.getEmployees().stream().filter(employee->employee.getSalary()>500000).toList();
        employees.forEach(System.out::println);
    }
    static List<Employee> getTaxedEmployees2(String input){
        return  (input.equalsIgnoreCase("Tax"))
                ? DataBase.getEmployees().stream().filter(emp->emp.getSalary()>500000).collect(Collectors.toList())
                    : DataBase.getEmployees().stream().filter(emp->emp.getSalary()<500000).collect(Collectors.toList());

    }
    @Test
    static void evaluateTax3(){
        System.out.println(getTaxedEmployees2("nTax"));

    }
    static Stream<Arguments> evaluateTax5(){
        return Stream.of(Arguments.of("Tax", "Tax"),
        Arguments.of("Tax","Tax"),
        Arguments.of("Tax","Tax"));

    }
    @ParameterizedTest
    @MethodSource("evaluateTax5")
    void evaluateTax4(String input, String input2){
        System.out.println(getTaxedEmployees2(input));
        System.out.println(getTaxedEmployees2(input2));
//    BiConsumer<List<Employee>, List<Employee>> addEmployee = (employees, employees2)->{
//        employees.addAll(employees2);
//        employees.addAll(employees);
//        employees.forEach(System.out::println);

    };
      int[] arrr(){
         int[] ar ={1,2,3};
        return ar;
    }
    //addEmployee.accept(DataBase.getEmployees(),DataBase.getEmployees());
}


