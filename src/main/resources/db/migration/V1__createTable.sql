CREATE TABLE quizes (
    id bigint NOT NULL PRIMARY KEY,
    quiz_title VARCHAR(255) NOT NULL,
    total_marks bigint NOT NULL,
    pass_marks bigint NOT NULL
)

CREATE TABLE questions (
    id bigint DEFAULT nextval('questions_id'::regclass) PRIMARY KEY,
    question_title character varying(255) NOT NULL,
    option1 character varying(255) NOT NULL,
    option2 character varying(255) NOT NULL,
    option3 character varying(255) NOT NULL,
    option4 character varying(255) NOT NULL,
    right_answer character varying(255) NOT NULL,
    difficulty_level character varying(255) NOT NULL,
    category character varying(255) NOT NULL
);