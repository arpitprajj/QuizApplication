package com.Quiz.Application.service.impl;

import com.Quiz.Application.dto.AnswerDto;
import com.Quiz.Application.entity.Answer;
import com.Quiz.Application.entity.Question;
import com.Quiz.Application.exception.ResourceNotFoundException;
import com.Quiz.Application.repository.AnswerRepository;
import com.Quiz.Application.repository.QuestionRepository;
import com.Quiz.Application.service.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public AnswerDto createAnswer(int questionId,AnswerDto answerDto) {
        Answer answer=this.modelMapper.map(answerDto,Answer.class);
        Question question=this.questionRepository.findById(questionId).orElseThrow(()->new ResourceNotFoundException("Question ","Questiomn Id",questionId));
        answer.setQuestion(question);
        Answer savedAns=this.answerRepository.save(answer);
        return this.modelMapper.map(savedAns, AnswerDto.class);
    }

    @Override
    public AnswerDto getAnswerById(int id) {
        Answer answer=this.answerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Answer","Answer Id",id));

        return this.modelMapper.map(answer, AnswerDto.class);
    }

    @Override
    public AnswerDto updateAnswerById(int id, AnswerDto answerDto) {
        Answer answer=this.answerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Answer","Answer Id",id));
        answer.setAns(answerDto.getAns());
        Answer savedAns=this.answerRepository.save(answer);
        return this.modelMapper.map(savedAns, AnswerDto.class);

    }

    @Override
    public void deleteAnswerById(int id) {
        Answer answer=this.answerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Answer","Answer Id",id));
     this.answerRepository.deleteById(id);
    }

    @Override
    public List<AnswerDto> searchAnswerbyValue(String keyword) {
        List<Answer>answers=this.answerRepository.findByAnsContaining(keyword);
        List<AnswerDto>answerDtos=answers.stream().map(p->this.modelMapper.map(p, AnswerDto.class)).collect(Collectors.toList());
        return answerDtos;
    }
}
