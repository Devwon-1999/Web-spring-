package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){ // 내부의 파라미터를 받는 컨트롤러
        model.addAttribute("data","Hello!!");
        return "hello";
    }
    @GetMapping("hello-MVC") // 외부의 파라미터를 받는 MVC 컨트롤러
    public String helloMVC(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
}
