package webshop.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class TestController {

    @RequestMapping(value="/api/test", method= RequestMethod.GET)
    String test() {
        return "Hello World";
    }
}
