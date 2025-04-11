package com.Quiz.Application.repository;

import com.Quiz.Application.entity.StudentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentResponseRepository extends JpaRepository<StudentResponse,Integer> {


    @Query(value = "SELECT COUNT(*) FROM student_response WHERE student_id = :studentId AND quiz_id = :quizId AND question_id = :questionId", nativeQuery = true)
    int countByStudentIdQuizIdAndQuestionId(@Param("studentId") Long studentId,
                                            @Param("quizId") Long quizId,
                                            @Param("questionId") Long questionId);
}
