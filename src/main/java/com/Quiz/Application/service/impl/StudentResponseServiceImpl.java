package com.Quiz.Application.service.impl;

import com.Quiz.Application.dto.ResponseByStudent;
import com.Quiz.Application.dto.ReviewDto;
import com.Quiz.Application.dto.StudentResponseDto;
import com.Quiz.Application.entity.Question;
import com.Quiz.Application.entity.Quiz;
import com.Quiz.Application.entity.Student;
import com.Quiz.Application.entity.StudentResponse;
import com.Quiz.Application.exception.ResourceNotFoundException;
import com.Quiz.Application.repository.*;
import com.Quiz.Application.service.AnswerService;
import com.Quiz.Application.service.QuestionService;
import com.Quiz.Application.service.StudentResponseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    QuestionService questionService;

    @Autowired
    ModelMapper modelMapper;





    @Override
    public StudentResponseDto createResponse(Integer stdId, Integer quizId, Integer questionId, String selOption) {
        Quiz quiz=this.quizRepository.findById(quizId).orElseThrow(()->new ResourceNotFoundException("Quiz","quiz id",quizId));
        Student student=this.studentRepository.findById(stdId).orElseThrow(()->new ResourceNotFoundException("Student","Student id",stdId));
        Question question=this.questionRepository.findById(questionId).orElseThrow(()->new ResourceNotFoundException("Question ","question id",questionId));

        int  exists = responseRepository.countByStudentIdQuizIdAndQuestionId(
                stdId,quizId,questionId
        );

        if (exists>0) {
            throw new IllegalStateException("This student has already submitted a response for this question in this quiz.");
        }
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
    public List<StudentResponse> getResponses() {
        List<StudentResponse> studentResponse=responseRepository.findAll();
        return studentResponse;
    }

    @Override
    public ResponseByStudent getResponseOfStudent(Integer stdId, Integer quizId) {
        Student student=this.studentRepository.findById(stdId).orElseThrow(()->new ResourceNotFoundException("Student","Student Id",stdId));
        List<StudentResponse>studentResponses=this.responseRepository.findByStudent(student);
        ResponseByStudent responseByStudent=new ResponseByStudent();
        responseByStudent.setStdId(stdId);
        responseByStudent.setEmail(student.getEmailId());
        responseByStudent.setQuizId(quizId);
        List<ReviewDto>review=new ArrayList<>();
        for(StudentResponse res:studentResponses){
            ReviewDto reviewDto=new ReviewDto();

            int questId=res.getQuestion().getId();
            reviewDto.setQuestionId(questId);
            reviewDto.setQuery(res.getQuestion().getQuery());
            reviewDto.setSelOption(res.getSelected_option());
            reviewDto.setCorOption(questionService.getAnswerByQuestionId(questId));
            review.add(reviewDto);
        }
        responseByStudent.setReviewDtos(review);
        return responseByStudent;
    }
}
