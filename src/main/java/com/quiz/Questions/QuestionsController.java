package com.quiz.Questions;


import com.quiz.Entities.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@ComponentScan
@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    QuestionsService questionsService;

    @GetMapping("/all")
    public Mono<ResponseEntity<List<Questions>>> GetAllQuestions(){
        return questionsService.GetAllQuestions();
    }

    @PostMapping("/add")
    public Mono<ResponseEntity<String>> addQuestion(@RequestBody Questions req){
        return questionsService.addQuestion(req);
    }

    @GetMapping("/category")
    public Mono<ResponseEntity<List<Questions>>> getQuestionsByFilter(@RequestParam(required = false) String category,
                                                                @RequestParam(required = false) String difficultyLevel,
                                                                @RequestParam(required = false) Long id){
        return questionsService.getQuestionsByFilter(category, difficultyLevel, id);
    }

    @DeleteMapping("/id/{id}")
    public Mono<ResponseEntity<String>> deleteById(@PathVariable Long id){
        return questionsService.deleteById(id);
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<String>> updateById(@RequestBody Questions req){
        return questionsService.updateById(req);
    }

}
