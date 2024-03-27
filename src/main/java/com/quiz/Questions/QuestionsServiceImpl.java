package com.quiz.Questions;

import com.quiz.Entities.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Mono<ResponseEntity<List<Questions>>> GetAllQuestions(){
        Mono<List<Questions>> res;
        res = questionRepository.getAllQuestions().collectList();
        return res.map(
                questionList-> new ResponseEntity<>(questionList, HttpStatus.OK))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<String>> addQuestion(Questions req) {
        Mono<Questions> saveMono = questionRepository.save(req);

        return saveMono
                .map(savedQuestion -> ResponseEntity.status(HttpStatus.CREATED).body("Successfully Added"))
                .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add question"));
    }

    @Override
    public Mono<ResponseEntity<List<Questions>>> getQuestionsByFilter(String category, String difficultyLevel, Long id){

        Mono<List<Questions>> resp;
        resp =
                questionRepository.findByCategoryAndDifficultyLevelAndId(category, difficultyLevel, id).collectList();
        return resp.map(
                questions -> new ResponseEntity<>(questions, HttpStatus.OK)
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<String>> deleteById(Long id){
        Mono<Boolean> ifExists = questionRepository.existsById(id);
        return ifExists.flatMap(exists -> {
            if (!exists) {
                return Mono.just(new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST));
            } else {
                return questionRepository.deleteById(id)
                        .then(Mono.just(new ResponseEntity<>("Successfully Deleted", HttpStatus.ACCEPTED)));
            }
        });
    }

    @Override
    public Mono<ResponseEntity<String>> updateById(Questions req){
        Mono<Questions> saveMono = questionRepository.save(req);

        return saveMono
                .map(savedQuestion -> ResponseEntity.status(HttpStatus.CREATED).body("Successfully Added"))
                .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add question"));
    }


}
