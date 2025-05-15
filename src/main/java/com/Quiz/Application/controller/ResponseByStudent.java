package com.Quiz.Application.controller;

import com.Quiz.Application.dto.ReviewDto;

import java.util.ArrayList;
import java.util.List;

public class ResponseByStudent {

    private int stdId;
    private String email;
    private int quizId;
    List<ReviewDto>reviewDtos=new ArrayList<>();
}
