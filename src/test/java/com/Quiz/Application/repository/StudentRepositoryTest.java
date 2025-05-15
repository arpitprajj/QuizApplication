package com.Quiz.Application.repository;

import com.Quiz.Application.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentRepositoryTest {
    @Mock
    StudentRepository studentRepository;

    @Test
    void createStudent(){
        Student student=new Student();
        student.setStudentId(1);
        student.setName("ved");
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        Student student1=studentRepository.save(student);
        Assertions.assertEquals(student1.getStudentId(),student.getStudentId());
        studentRepository.deleteById(1);
        Mockito.verify(studentRepository,Mockito.times(1)).deleteById(1);

    }

}