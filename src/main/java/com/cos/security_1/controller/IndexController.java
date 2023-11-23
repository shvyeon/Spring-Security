package com.cos.security_1.controller;

import com.cos.security_1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // View 를 티턴하겠다
public class IndexController {
    @GetMapping({"","/"})
    public String index(){
        // 머스테치 기본폴더 src/main/resources/
        // 뷰 리졸버 설정 : templates (prefix), .mustache (suffix) 생략가능 !
        return "index"; // src/main/resources/templates/index.mustache
    }

    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }


}
