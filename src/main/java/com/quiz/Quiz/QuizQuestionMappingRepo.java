package com.quiz.Quiz;

import com.quiz.Entities.QuizQuestionsMapping;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionMappingRepo extends ReactiveCrudRepository<QuizQuestionsMapping, Long> {
}
