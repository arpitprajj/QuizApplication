package com.Quiz.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {
    private int id;
    private int quizId;
    private int studentId;
    private int questionId;
    private String query;
    private  String sel_option;
}
