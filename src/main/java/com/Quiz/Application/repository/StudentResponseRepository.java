package com.Quiz.Application.repository;

import com.Quiz.Application.entity.StudentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentResponseRepository extends JpaRepository<StudentResponse,Integer> {
}
