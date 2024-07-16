package demo.gwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GWT service API
 *
 * @author cxq
 * @date 2024/7/13 17:56
 */
@CrossOrigin(origins = "http://127.0.0.1:8888")
@RestController
@RequestMapping("/api")
public class GWTController {

    @GetMapping("/sayHello")
    public String sayHello() {
        return "hello world";
    }
}
