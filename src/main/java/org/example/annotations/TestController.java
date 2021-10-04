package org.example.annotations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final Test test;

    public TestController(Test test) {
        this.test = test;
    }

    @GetMapping("/test")
    public String test() {
        return test.getString();
    }
}
