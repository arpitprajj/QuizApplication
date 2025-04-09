package com.Quiz.Application.service;

import com.Quiz.Application.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);
    StudentDto getStudentById(Integer id);
    List<StudentDto>getAllStudents();
    StudentDto updateStudent(Integer id,StudentDto studentDto);
    void deleteStudent(Integer id);
    List<StudentDto>searchStudentByName(String name);
}
