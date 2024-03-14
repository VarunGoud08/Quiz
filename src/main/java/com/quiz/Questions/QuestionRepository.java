package com.quiz.Questions;

import com.quiz.Entities.Questions;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface QuestionRepository extends ReactiveCrudRepository<Questions, Long> {

    @Query(
            """
                    SELECT * FROM questions
                    """
    )
    Flux<Questions> getAllQuestions();

    @Query(
            """
                       INSERT INTO questions (question_title, option1, option2, option3, option4, right_answer, difficulty_level, category)
                               VALUES
                       ("adsfgvjhbk", '23', '46', '91', '125', '23', 'Easy', 'math')
                    """

            )
    void saveQuestion(Questions req);

    @Query(
            """
                    SELECT * FROM questions WHERE (:category IS NULL OR category = :category)
                                                AND (:difficultyLevel IS NULL OR difficulty_level = :difficultyLevel)
                                                AND (:id IS NULL OR id = :id)
                              
                    """
    )
    Flux<Questions> findByCategoryAndDifficultyLevelAndId(String category, String difficultyLevel, Long id);

    Flux<Questions> findByCategoryAndDifficultyLevel(String category, String difficultyLevel);

    Flux<Questions> findByCategory(String category);
}
