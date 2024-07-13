package demo.gwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GWT API
 *
 * @author cxq
 * @date 2024/7/13 17:56
 */
@RestController
@RequestMapping("/api")
public class GWTController {

    @GetMapping("/hello")
    public String sayHello() {
        return "你好呀";
    }
}
