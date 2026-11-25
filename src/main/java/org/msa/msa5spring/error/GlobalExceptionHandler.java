package org.msa.msa5spring.error;

import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 여러 컨트롤러에 공통으로 적용되는 전역(Global) 처리 클래스를 지정하는 어노테이션
@RestControllerAdvice
public class GlobalExceptionHandler {
    // Method level에 쓰는 어노테이션이 중요
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponseDTO<Void>> handle() {
        return ResponseEntity
        .status(500) // 서버 내부 오류
        .body(
                new GlobalResponseDTO<>(
                        "E99",
                        "시스템 에러",
                        null
                )
        );
    }
}
