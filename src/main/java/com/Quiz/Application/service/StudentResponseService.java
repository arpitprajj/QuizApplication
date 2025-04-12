package com.Quiz.Application.service;

import com.Quiz.Application.dto.StudentResponseDto;
import com.Quiz.Application.entity.StudentResponse;

import java.util.List;

public interface StudentResponseService {

  StudentResponseDto createResponse(Integer stdId,Integer quizId,Integer questionId,String selOption);

  List<StudentResponse>getResponses();
}
