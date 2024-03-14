package com.quiz.Questions;


import com.quiz.Entities.Questions;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface QuestionsService {

     Mono<ResponseEntity<List<Questions>>> GetAllQuestions();

     Mono<ResponseEntity<String>> addQuestion(Questions req);

     Mono<ResponseEntity<List<Questions>>> getQuestionsByFilter(String category, String difficultyLevel, Long id);

     Mono<ResponseEntity<String>> deleteById(Long id);

     Mono<ResponseEntity<String>> updateById(Questions req);
}
