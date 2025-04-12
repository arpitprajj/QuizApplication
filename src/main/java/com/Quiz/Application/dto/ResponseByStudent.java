package com.Quiz.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseByStudent {
    private int stdId;
    private String email;
    private int quizId;
    List<ReviewDto> reviewDtos=new ArrayList<>();
}
