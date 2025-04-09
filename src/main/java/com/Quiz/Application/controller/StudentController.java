package com.Quiz.Application.controller;

import com.Quiz.Application.dto.StudentDto;
import com.Quiz.Application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    ResponseEntity<StudentDto>createStudent(@RequestBody StudentDto studentDto){
        StudentDto studentDto1=this.studentService.createStudent(studentDto);
        return new ResponseEntity<>(studentDto1, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    ResponseEntity<StudentDto>getStudent(@PathVariable Integer id){
        StudentDto studentDto=this.studentService.getStudentById(id);
        return new ResponseEntity<>(studentDto,HttpStatus.FOUND);
    }
    @GetMapping
    ResponseEntity<List<StudentDto>>getAllStudent(){
        List<StudentDto>studentDtos=this.studentService.getAllStudents();
        return  new ResponseEntity<>(studentDtos,HttpStatus.FOUND);
    }

    @DeleteMapping("{id}")
    ResponseEntity<String>deleteStudent(@PathVariable Integer id){
        this.studentService.deleteStudent(id);
        return new ResponseEntity<>("Student deleted successfully",HttpStatus.OK);
    }
    @GetMapping("/search/{name}")
    ResponseEntity<List<StudentDto>>searchStudentByName(@PathVariable String name){
        List<StudentDto>studentDtos=this.studentService.searchStudentByName(name);
        return new ResponseEntity<>(studentDtos,HttpStatus.FOUND);
    }

    @PutMapping("{id}")
    ResponseEntity<StudentDto>updateStudent(@PathVariable Integer id,@RequestBody StudentDto studentDto){
      StudentDto dto=this.studentService.updateStudent(id,studentDto);
      return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
