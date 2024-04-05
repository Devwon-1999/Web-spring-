package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping("hello-string")
    @ResponseBody //http의 Body부분에 내용을 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }
    //hello MVC와 hello-string둘의 차이는 소스보기에서 달라진다

    @GetMapping("hello-api") //Api 방식이고 json형식(속성-값)으로 값이 넘어오게된다.
    @ResponseBody //default값이 json이다.
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // "<HTML></HTML>"; xml방식 무겁고 태그를 2번씩 사용해야해서 번거로워 최근엔 사용하지 않는다
    }
    static class Hello{
        private String name;
        // getter과 setter은 ctrl + space 후 get과 set 검색하여 자동 완성할 수 있다.
        // 자바 빈 표준 방식이고 메서드를 통해 접근한다
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }
    // Api는 실제로 자주 사용된다. 최근프로젝트에서

}