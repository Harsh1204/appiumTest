package com.example.appiumtest.testStream;

import java.util.ArrayList;
import java.util.List;

public class DataBase {


    public static List<Employee> getEmployees() {
        List<Employee> list=new ArrayList<>();
        list.add(new Employee(176,"roshan","IT",600000));
        list.add(new Employee(388,"Bikas","CIVIL",900000));
        list.add(new Employee(470,"Bimal","DEFENCE",500000));
        list.add(new Employee(624,"Sourav","CORE",400000));
        list.add(new Employee(176,"Prakash","SOCIAL",1200000));
        return list;
    }
}
