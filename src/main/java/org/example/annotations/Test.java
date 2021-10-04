package org.example.annotations;

import org.springframework.stereotype.Component;

@Component
public class Test {
    public void hello(){
        System.out.println("Hello2");
    }

    public String getString(){
        return "Hello World!";
    }
}
