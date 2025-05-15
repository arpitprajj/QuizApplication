package com.Quiz.Application.service;

import com.Quiz.Application.dto.StudentDto;
import com.Quiz.Application.entity.Student;
import com.Quiz.Application.repository.StudentRepository;
import com.Quiz.Application.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    StudentRepository repository;
    @InjectMocks
    StudentServiceImpl studentService;

    @Mock
    ModelMapper modelMapper;

    @Test
    void createStudentTest() {
        StudentDto studentDto=new StudentDto();
        studentDto.setStudentId(1);
        studentDto.setName("ved");

        Student student=new Student();student.setStudentId(studentDto.getStudentId());student.setName(studentDto.getName());
        Mockito.when(modelMapper.map(studentDto,Student.class)).thenReturn(student);
        Mockito.when(repository.save(student)).thenReturn(student);
        Mockito.when(modelMapper.map(student,StudentDto.class)).thenReturn(studentDto);

        StudentDto created=studentService.createStudent(studentDto);
        Assertions.assertEquals(student.getStudentId(),created.getStudentId());
        Mockito.verify(repository,Mockito.times(0)).delete(student);
    }


}