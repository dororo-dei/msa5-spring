package org.msa.msa5spring.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Employee {
    // 객체 Long을 쓰는 이유 emp_id가 비어있어야함 auto increment해야함 null로해야함 기본형은 null이 안됨 그래서 객체타입으로 통일
    private Long empId;
    private String name;
    private LocalDate birth;
    private Character gender;
    private LocalDate hireAt;
    private LocalDate fireAt;
    private Long supId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
