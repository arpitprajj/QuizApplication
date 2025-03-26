package com.Quiz.Application.service.impl;

import com.Quiz.Application.dto.QuestionDto;
import com.Quiz.Application.dto.QuizDto;
import com.Quiz.Application.entity.Question;
import com.Quiz.Application.entity.Quiz;
import com.Quiz.Application.exception.ResourceNotFoundException;
import com.Quiz.Application.repository.QuestionRepository;
import com.Quiz.Application.repository.QuizRepository;
import com.Quiz.Application.service.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public QuizDto createQuiz(QuizDto quizDto) {
        if(quizDto.getAddedDate()==null)
            quizDto.setAddedDate(new Date());


        Quiz quiz=this.modelMapper.map(quizDto,Quiz.class);
        Quiz savedQuiz=this.quizRepository.save(quiz);
        return this.modelMapper.map(savedQuiz,QuizDto.class);
    }

    @Override
    public QuizDto getQuizbyId(int id) {
        Quiz quiz=this.quizRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Quiz","Quiz Id",id));
        return this.modelMapper.map(quiz,QuizDto.class);
    }

    @Override
    public List<QuizDto> getAllQuiz() {
        List<Quiz>quizList=this.quizRepository.findAll();
        List<QuizDto>dtoList=quizList.stream().map(p->this.modelMapper.map(p,QuizDto.class)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public QuizDto updateQuizById(int id, QuizDto quizDto) {
        Quiz quiz=this.quizRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Quiz","Quiz Id",id));
        quiz.setTitle(quizDto.getTitle());
        if(quizDto.getAddedDate()==null)
            quizDto.setAddedDate(new Date());
        quiz.setAddedDate(quizDto.getAddedDate());
        quiz.setQuestions(quizDto.getQuestions());
        Quiz quiz1=this.quizRepository.save(quiz);

        return this.modelMapper.map(quiz1,QuizDto.class);


    }

    @Override
    public void deleteQuizById(int id) {
        this.quizRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Quiz","Quiz Id",id));
      this.quizRepository.deleteById(id);
    }

    @Override
    public QuizDto createQuizRandom(int numQ, String title) {
        List<Question>questions=this.questionRepository.findRandomQuestion(numQ);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setAddedDate(new Date());
        quiz.setQuestions(questions);
        Quiz quiz1=this.quizRepository.save(quiz);
        return this.modelMapper.map(quiz1,QuizDto.class);
    }

    @Override
    public QuizDto addQuestion(int quizId, QuestionDto questionDto) {
        Quiz quiz=this.quizRepository.findById(quizId).orElseThrow(()->new ResourceNotFoundException("Quiz ","Quiz Id",quizId));
        //Question question=this.questionRepository.findById(questionId).orElseThrow(()->new ResourceNotFoundException("Question","Question Id",questionId));
        List<Question>questionList=quiz.getQuestions();
        Question question=this.modelMapper.map(questionDto,Question.class);
        questionList.add(question);
        quiz.setQuestions(questionList);
        Quiz quiz1=this.quizRepository.save(quiz);

        return this.modelMapper.map(quiz1,QuizDto.class);
    }
}
