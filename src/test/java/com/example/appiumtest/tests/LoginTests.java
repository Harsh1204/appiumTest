package com.example.appiumtest.tests;

import io.micrometer.observation.Observation;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.cglib.core.internal.Function;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LoginTests {
    String appPath=System.getProperty("appPath");
    String path =System.getProperty("user.dir");

    @org.junit.jupiter.api.Test
    void tst9(){

        System.out.println(path);
        System.out.println("AppPath"+path+appPath);
    }
    @Test
    void tst(){
        Runnable helloWorld = () -> System.out.println("Hello World");
        helloWorld.run();
    }

    @Test
    void tst1(){
        BiConsumer< Integer ,Integer> i=(a,b)->{System.out.println(a+b);};
        BiConsumer< Integer ,Integer> i1=(a,b)->{System.out.println(a*b);};
        //i.accept(1,9);
        i.andThen(i).andThen(i1).accept(3,9);







    }
    @Test
    void tst2(){
        Function<Integer,Integer> i=(a)->{System.out.println(a+a);

            return a;
        };
        //BiConsumer< Integer ,Integer> i1=(a,b)->{System.out.println(a*b);};
        //i.accept(1,9);
        i.apply(3);

    }
    @Test
    void test3(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
//        for(String s: list){
//            System.out.println(s);
//        }
        list.forEach(e-> {
            if(e.contains("f")){
                System.out.println(e);

                System.out.println(e.toUpperCase());
            }

        });
        list.stream().filter(e -> e.contains("f")).map(String::toUpperCase).forEach(System.out::println);
    }
    @Test
    void test4(){
        Map<String,String> map = new HashMap<>();
        map.put("a","b");
        map.put("c","d");
        map.put("e","f");
        map.put("f","g");
        map.put("g","h");
        map.forEach((k,v)->{
            if(k.equals("a")){
                System.out.println(v);
            }
        });
    }
}

