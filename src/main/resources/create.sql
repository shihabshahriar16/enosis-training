create table STUDENT (
	STUDENT_ID char(36),
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255),
    ADDRESS varchar(255)
);

create table `SUBJECT` (
	SUBJECT_ID char(36),
    SUBJECT_TITLE varchar(36)
);

create table STUDENT_SUBJECT_MAPPING (
	STUDENT_SUBJECT_MAPPING_ID char(36),
    STUDENT_ID char(36),
    SUBJECT_ID char(36)
);

create table STUDENT_RESULT_MAPPING (
	RESULT_ID char(36),
	STUDENT_SUBJECT_MAPPING_ID char(36),
    SCORE int
);

