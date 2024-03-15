CREATE TABLE quizes (
    id bigint NOT NULL PRIMARY KEY,
    quiz_title VARCHAR(255) NOT NULL,
    total_marks bigint NOT NULL,
    pass_marks bigint NOT NULL
)