package com.quiz.Quiz;

import com.quiz.Entities.Quizes;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends ReactiveCrudRepository<Quizes, Long> {


}
