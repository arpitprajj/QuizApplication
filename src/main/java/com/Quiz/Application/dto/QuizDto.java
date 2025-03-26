package com.Quiz.Application.dto;

import com.Quiz.Application.entity.Question;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {

    private int id;
    private String title;
    private Date addedDate;

    private List<Question> questions=new ArrayList<>();
}
