package com.Quiz.Application.service;

import com.Quiz.Application.QuizApplication;
import com.Quiz.Application.dto.QuestionDto;

import java.util.List;

public interface QuestionService {

     QuestionDto createQuestion(QuestionDto questionDto);
     List<QuestionDto> getAllQuestions();
     QuestionDto getQuestionById(Integer id);
     QuestionDto updateQuestionById(QuestionDto questionDto,Integer id);
     void deleteQuestion(Integer id);
     List<QuestionDto>searchQuestions(String keyword);

     String getAnswerByQuestionId(Integer id);
}
