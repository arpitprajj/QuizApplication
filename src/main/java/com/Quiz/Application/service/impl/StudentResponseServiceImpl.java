package com.Quiz.Application.service.impl;

import com.Quiz.Application.dto.StudentResponseDto;
import com.Quiz.Application.entity.Question;
import com.Quiz.Application.entity.Quiz;
import com.Quiz.Application.entity.Student;
import com.Quiz.Application.entity.StudentResponse;
import com.Quiz.Application.exception.ResourceNotFoundException;
import com.Quiz.Application.repository.QuestionRepository;
import com.Quiz.Application.repository.QuizRepository;
import com.Quiz.Application.repository.StudentRepository;
import com.Quiz.Application.repository.StudentResponseRepository;
import com.Quiz.Application.service.StudentResponseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentResponseServiceImpl implements StudentResponseService {

    @Autowired
    StudentResponseRepository responseRepository;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper modelMapper;





    @Override
    public StudentResponseDto createResponse(Integer stdId, Integer quizId, Integer questionId, String selOption) {
        Quiz quiz=this.quizRepository.findById(quizId).orElseThrow(()->new ResourceNotFoundException("Quiz","quiz id",quizId));
        Student student=this.studentRepository.findById(stdId).orElseThrow(()->new ResourceNotFoundException("Student","Student id",stdId));
        Question question=this.questionRepository.findById(questionId).orElseThrow(()->new ResourceNotFoundException("Question ","question id",questionId));
        StudentResponse studentResponse=new StudentResponse();
        studentResponse.setQuiz(quiz);
        studentResponse.setStudent(student);
        studentResponse.setQuestion(question);
        studentResponse.setSelected_option(selOption);
        StudentResponse savedResponse=this.responseRepository.save(studentResponse);
        StudentResponseDto responseDto=new StudentResponseDto();
        responseDto.setId(savedResponse.getId());
        responseDto.setStudentId(savedResponse.getStudent().getStudentId());
        responseDto.setQuestionId(savedResponse.getQuestion().getId());
        responseDto.setQuizId(savedResponse.getQuiz().getId());
        responseDto.setQuery(savedResponse.getQuestion().getQuery());
        responseDto.setSel_option(selOption);


        return responseDto;
    }

    @Override
    public List<StudentResponseDto> getResponses() {
        return List.of();
    }
}
