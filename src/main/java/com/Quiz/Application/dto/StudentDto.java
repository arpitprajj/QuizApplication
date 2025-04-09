package com.Quiz.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private int studentId;
    private String name;
    private String password;
    private String rollNo;
    private String emailId;
    private String branch;
    private String college;
    private String mobileNo;
}
