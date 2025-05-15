package com.Quiz.Application.controller;

import com.Quiz.Application.dto.StudentDto;
import com.Quiz.Application.entity.Student;
import com.Quiz.Application.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    StudentService studentService;
    @InjectMocks
    StudentController controller;
    @Test
    void createStudentTest() {
        StudentDto studentDto=new StudentDto();
        studentDto.setStudentId(1);
        studentDto.setName("Ved");
        Mockito.when(studentService.createStudent(studentDto)).thenReturn(studentDto);
        ResponseEntity<StudentDto>dto=controller.createStudent(studentDto);
        Assertions.assertEquals(dto.getBody().getStudentId(),studentDto.getStudentId());


    }
}