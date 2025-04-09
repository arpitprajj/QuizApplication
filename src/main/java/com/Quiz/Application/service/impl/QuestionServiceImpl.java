package com.Quiz.Application.service.impl;

import com.Quiz.Application.QuizApplication;
import com.Quiz.Application.dto.QuestionDto;
import com.Quiz.Application.entity.Question;
import com.Quiz.Application.exception.ResourceNotFoundException;
import com.Quiz.Application.repository.QuestionRepository;
import com.Quiz.Application.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        Question question=this.modelMapper.map(questionDto,Question.class);
        Question createdQuestion=this.questionRepository.save(question);

        return this.modelMapper.map(createdQuestion,QuestionDto.class);
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        List<Question> question=this.questionRepository.findAll();


        List<QuestionDto> questionDtostoList= question.stream().map(p->this.modelMapper.map(p,QuestionDto.class)).collect(Collectors.toList());
        return  questionDtostoList;
    }

    @Override
    public QuestionDto getQuestionById(Integer id) {
        Question question=this.questionRepository.findById(id).orElseThrow(()-> new  ResourceNotFoundException("Question","Question Id",id));
        return this.modelMapper.map(question,QuestionDto.class);
    }

    @Override
    public QuestionDto updateQuestionById(QuestionDto questionDto, Integer id) {
        Question question=this.questionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Question","Question Id",id));

        question.setQuery(questionDto.getQuery());
        question.setOption1(questionDto.getOption1());
        question.setOption2(questionDto.getOption2());
        question.setOption3(questionDto.getOption3());
        question.setOption4(questionDto.getOption4());
        Question updateQuestion =this.questionRepository.save(question);
        return this.modelMapper.map(updateQuestion,QuestionDto.class);
    }

    @Override
    public void deleteQuestion(Integer id) {

        this.questionRepository.deleteById(id);
    }

    @Override
    public List<QuestionDto> searchQuestions(String keyword) {
        List<Question>questionList=this.questionRepository.findByQueryContaining(keyword);
        List<QuestionDto>dtoList=questionList.stream().map(p->this.modelMapper.map(p,QuestionDto.class)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public String getAnswerByQuestionId(Integer id) {

        return this.questionRepository.findAnswer(id);
    }
}
