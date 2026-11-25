package org.msa.msa5spring.controller;

import org.msa.msa5spring.request.QueryRequestDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// 스프링부트에 이 클래스가 컨트롤러 역할을 한다고 알려준다
@RestController
@RequestMapping("/api")
public class RequestMappingController {

    // GET(행위)/users(자원)
    @GetMapping
    public String index(){
        return "유저 조회";
    }

    @PostMapping
    public String store(){
        return "유저 생성";
    }

    @PutMapping
    public String update() {
        return "유저 수정";
    }

    @DeleteMapping
    public String destroy() {
        return "유저 삭제";
    }

    // 1. 쿼리 파라미터
    @GetMapping("/query")
    public String query(QueryRequestDTO queryRequestDTO) {
        return String.format("%d : %s", queryRequestDTO.id(),queryRequestDTO.name());
    }
    // 2. 세그먼트 파라미터
    @GetMapping("/segment/{id}/{name}")
    public String segment(@ModelAttribute QueryRequestDTO queryRequestDTO) {
        return String.format("segment = %d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }

    // 세그먼트 파라미터를 이용한 Path와 겹치지 않도록 주의
    // @GetMapping("/segment/1/my")
    // public String test() {
    //     return "test";
    // }

    // 3. form-data
    @PostMapping("/form")
    public String form(@ModelAttribute MultipartFile file) {
        return String.format("%s : %s", file.getContentType(), file.getOriginalFilename());
    }

    // 4. JSON
    @GetMapping("/json")
    public String json(@RequestBody QueryRequestDTO queryRequestDTO) {
        return String.format("json = %s : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }
}
// Query Parameter 방식
// - 어노테이션 X, DTO객체 생성

// Segment Parameter 방식
// - @PathVariable, 각 키에 맞는 파라미터를 별도로 컨트롤러 파라미터로 생성
// - @ModelAttribute, DTO객체 생성

// Form Data 방식
// -@ModelAttribute, DTO 객체 또는 컨트롤러 파라미터

// JSON 방식
// -@RequestBody, DTO 객체

