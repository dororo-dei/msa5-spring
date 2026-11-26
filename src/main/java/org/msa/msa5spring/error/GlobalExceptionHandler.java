package org.msa.msa5spring.error;

import lombok.extern.slf4j.Slf4j;
import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;
import java.util.stream.Collectors;

// 모든 컨트롤러에서 발생하는 예외를 공통으로 처리
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // --------------------------------------------------
    // 1. 요청 데이터의 타입 불일치 예외 처리
    // --------------------------------------------------

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GlobalResponseDTO<String>> handle(
            MethodArgumentTypeMismatchException e) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // HTTP 상태 코드 400
                .body(
                        new GlobalResponseDTO<>(
                                "E01", // 사용자 정의 에러 코드
                                "유효성 체크 에러",
                                String.format(
                                        "%s:필드를 확인해 주세요.",
                                        e.getName() // 오류가 발생한 요청 항목명
                                )
                        )
                );
    }

    // --------------------------------------------------
    // 2. 요청 데이터의 유효성 검사 실패 예외 처리
    // --------------------------------------------------

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponseDTO<Map<String, String>>> handle(MethodArgumentNotValidException e) {

        Map<String, String> errors = e.getBindingResult()
                .getFieldErrors() // 필드별 유효성 검사 오류 목록 가져오기
                .stream() // 오류 목록을 Stream으로 변환
                .collect(Collectors.toMap( // 오류 목록을 Map으로 변환

                        // Key: 오류가 발생한 필드명
                        FieldError::getField,

                        // Value: 오류 메시지, null이면 기본 메시지 사용
                        fieldError -> fieldError.getDefaultMessage() != null
                                ? fieldError.getDefaultMessage()
                                : "유효하지 않은 값입니다.",

                        // Key가 중복되면 기존 오류 메시지 유지
                        (existing, replacement) -> existing

                )); // 최종 연산: Map 생성
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400
                .body(
                        new GlobalResponseDTO(
                                "E01",
                                "유효성 체크 에러",
                                errors
                        )
                );
    }

    // --------------------------------------------------
    // 3. 별도로 처리하지 않은 일반적인 예외 처리
    // --------------------------------------------------

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponseDTO<Void>> handle(Exception e) {
        log.trace("트레이스 로깅");
        log.debug("디버그 로깅");
        log.info("인포 로깅");
        log.warn("워닝 로깅");
        log.error("에러 로깅");

        return ResponseEntity
                .status(500) // HTTP 상태 코드 500
                .body(new GlobalResponseDTO<>(
                        "E99",
                        "시스템 에러",
                        null // 반환할 결과 데이터 없음
                ));
    }
}