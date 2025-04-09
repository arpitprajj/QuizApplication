package com.Quiz.Application.service.impl;

import com.Quiz.Application.dto.StudentDto;
import com.Quiz.Application.entity.Student;
import com.Quiz.Application.exception.ResourceNotFoundException;
import com.Quiz.Application.repository.StudentRepository;
import com.Quiz.Application.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student=this.modelMapper.map(studentDto,Student.class);
        Student savedStd=this.studentRepository.save(student);
        return this.modelMapper.map(savedStd,StudentDto.class);
    }

    @Override
    public StudentDto getStudentById(Integer id) {
        Student student=this.studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student ","Student Id",id));

        return this.modelMapper.map(student,StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student>students=this.studentRepository.findAll();
        List<StudentDto>studentDtos=students.stream().map(p->this.modelMapper.map(p,StudentDto.class)).collect(Collectors.toList());
        return studentDtos;
    }

    @Override
    public StudentDto updateStudent(Integer id, StudentDto studentDto) {
        Student student=this.studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student ","Student Id",id));
        if(studentDto.getCollege()!=null) student.setCollege(studentDto.getCollege());
        if(studentDto.getBranch()!=null) student.setBranch(studentDto.getBranch());
        if(studentDto.getEmailId()!=null) student.setEmailId(studentDto.getEmailId());
        if(studentDto.getName()!=null) student.setName(studentDto.getName());
        if(studentDto.getMobileNo()!=null) student.setMobileNo(studentDto.getMobileNo());
        if(studentDto.getPassword()!=null) student.setPassword(studentDto.getPassword());
        if(studentDto.getRollNo()!=null) student.setRollNo(studentDto.getRollNo());
       Student savedStd=this.studentRepository.save(student);
        return this.modelMapper.map(savedStd,StudentDto.class);
    }

    @Override
    public void deleteStudent(Integer id) {
        Student student=this.studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student ","Student Id",id));
              this.studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDto> searchStudentByName(String name) {
        List<Student>students=this.studentRepository.findByNameContaining(name);
        List<StudentDto>studentDtos=students.stream().map(p->this.modelMapper.map(p,StudentDto.class)).collect(Collectors.toList());

        return studentDtos;
    }
}
