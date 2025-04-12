package com.Quiz.Application.service;

import com.Quiz.Application.dto.AnswerDto;

import java.util.List;

public interface AnswerService {

    AnswerDto createAnswer(int questionId,AnswerDto answer);
    AnswerDto getAnswerById(int id);
    AnswerDto updateAnswerById(int id, AnswerDto answer);
    void  deleteAnswerById(int id);
    List<AnswerDto>searchAnswerbyValue(String keyword);
}
