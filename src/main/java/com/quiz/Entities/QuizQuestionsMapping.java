package com.quiz.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class QuizQuestionsMapping {

    @Id
    private Integer id;
    private Integer quizId;
    private Integer questionId;
}
