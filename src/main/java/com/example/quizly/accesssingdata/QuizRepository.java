package com.example.quizly.accesssingdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("SELECT q FROM Quiz q WHERE q.code = :code")
    Quiz findByCode(@Param("code") String code);

    @Query("SELECT quiz FROM Quiz quiz WHERE quiz.user.userId = ?1")
    List<Quiz> getBidsByProductID(Long productID);
}
