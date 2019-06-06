package webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webshop.entities.Test;
import webshop.services.TestService;

import java.util.List;

@RestController
public class TestController {

    private TestService testService;

    @Autowired
    TestController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value="/api/test", method= RequestMethod.GET)
    public List<Test> test() {
        return testService.findAll();
    }

    @RequestMapping(value="/api/test", method= RequestMethod.POST)
    public void addTest() {
        testService.save(new Test("Hello World"));
    }
}
