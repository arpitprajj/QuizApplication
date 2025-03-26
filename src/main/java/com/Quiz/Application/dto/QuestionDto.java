package com.Quiz.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    private int id;
    private String query;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
}
