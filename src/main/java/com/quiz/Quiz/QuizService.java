package com.quiz.Quiz;

import com.quiz.Entities.Quizes;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface QuizService {
    Mono<ResponseEntity<String>> createQuiz(Quizes req, String category, String difficultyLevel);
}
