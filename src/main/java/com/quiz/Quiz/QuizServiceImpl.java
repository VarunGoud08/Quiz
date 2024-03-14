package com.quiz.Quiz;

import com.quiz.Entities.Questions;
import com.quiz.Entities.QuizQuestionsMapping;
import com.quiz.Entities.Quizes;
import com.quiz.Questions.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizQuestionMappingRepo quizQuestionMappingRepo;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Mono<ResponseEntity<String>> createQuiz(Quizes req, String category, String difficultyLevel){
        Mono<Quizes> savedQuizMono = quizRepository.save(req);
        Mono<List<Questions>> questionIdsFlux = (difficultyLevel != null) ?
                questionRepository.findByCategoryAndDifficultyLevel(category, difficultyLevel).collectList() :
                questionRepository.findByCategory(category).collectList();
        return savedQuizMono.flatMap(savedQuiz -> {
            // Save quiz-question mappings
            Mono<Void> saveMappingsMono = questionIdsFlux.flatMap(questionIds ->
                    Flux.fromIterable(questionIds)
                            .flatMap(questionId -> {
                                QuizQuestionsMapping mapping = new QuizQuestionsMapping();
                                mapping.setQuizId(savedQuiz.getId());
                                mapping.setQuestionId(questionId.getId());
                                return quizQuestionMappingRepo.save(mapping);
                            })
                            .then());

            // Wait for all mappings to be saved and then return success response
            return saveMappingsMono.thenReturn(ResponseEntity.ok("Quiz created successfully"));
        });
    }
}
