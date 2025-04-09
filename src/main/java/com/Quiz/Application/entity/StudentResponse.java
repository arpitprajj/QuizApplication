package com.Quiz.Application.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String respId;

    private String selected_option;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id",referencedColumnName = "id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "studentId")
    private  Student student;
}
