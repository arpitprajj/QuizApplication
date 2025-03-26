package com.Quiz.Application.service;

import com.Quiz.Application.dto.QuestionDto;
import com.Quiz.Application.dto.QuizDto;
import com.Quiz.Application.entity.Question;
import com.Quiz.Application.entity.Quiz;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface QuizService {

    QuizDto createQuiz(QuizDto quizDto);
    QuizDto getQuizbyId(int id);
    List<QuizDto>getAllQuiz();
    QuizDto updateQuizById(int id,QuizDto quizDto);
    void deleteQuizById(int id);
    QuizDto createQuizRandom(int numQ,String title);

    QuizDto addQuestion(int QuizId, QuestionDto questionDto);


}
