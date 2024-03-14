package com.quiz.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Quizes {

    @Id
    private Integer id;
    private String quizTitle;
    private Long totalMarks;
    private Long passMarks;
}
