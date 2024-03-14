package com.quiz.Quiz;

import com.quiz.Entities.Quizes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/add")
    public Mono<ResponseEntity<String>> createQuiz (@RequestBody Quizes req, @RequestParam String category, @RequestParam (required= false) String difficultyLevel){

        return quizService.createQuiz(req, category, difficultyLevel);
    }
}
