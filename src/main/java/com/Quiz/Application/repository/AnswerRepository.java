package com.Quiz.Application.repository;

import com.Quiz.Application.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    List<Answer>findByAnsContaining(String keyword);
}
