package org.msa.msa5spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// 스프링부트에 이 클래스가 컨트롤러 역할을 한다고 알려준다
@RestController
public class RequestMappingController {

    // GET/users
    @GetMapping("/users")
    public String index(){
        return "유저 조회";
    }
    // 코드 수정 시 재시작 할 것
    @PostMapping("/users")
    public String store(){
        return "유저 생성";
    }
}
