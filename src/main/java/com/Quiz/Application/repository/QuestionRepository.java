package com.Quiz.Application.repository;

import com.Quiz.Application.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {


 List<Question>findByQueryContaining( String keyword);
 @Query(value = "SELECT * FROM question ORDER BY RAND() LIMIT :number", nativeQuery = true)
 List<Question> findRandomQuestion(@Param("number") Integer number);
}
