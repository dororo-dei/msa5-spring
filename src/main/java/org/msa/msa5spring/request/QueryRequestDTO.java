package org.msa.msa5spring.request;

public record QueryRequestDTO(
        Long id, // 파라미터명이 필드명인데 이것과 key의 이름이 동일해야한다
        String name
) {}
