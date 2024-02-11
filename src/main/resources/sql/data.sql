-- DML 작성
-- department
insert into department
values (seq_department_id.nextval, '인사총무');
insert into department
values (seq_department_id.nextval, '경영지원');
insert into department
values (seq_department_id.nextval, 'Tech 개발');
insert into department
values (seq_department_id.nextval, '마케팅 사업');

-- position
insert into position
values (seq_position_id.nextval, '대표', 1);
insert into position
values (seq_position_id.nextval, '임원', 2);
insert into position
values (seq_position_id.nextval, '수석', 3);
insert into position
values (seq_position_id.nextval, '책임', 4);
insert into position
values (seq_position_id.nextval, '선임', 5);
insert into position
values (seq_position_id.nextval, '전임', 6);
insert into position
values (seq_position_id.nextval, '주임', 7);
insert into position
values (seq_position_id.nextval, '사원', 8);

-- employee
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '고혜진', 15, 'jindamgom@google.com', '010-1234-6864', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 251, 1);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '김명준', 15, 'myeongjun0911@google.com/', '010-1234-6865', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 51, 51);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '김정효', 15, 'kimjeonghyo1@google.com/', '010-1234-6866', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 101, 101);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '성민준', 15, 'seoungminjun@google.com/', '010-1234-6867', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 151, 151);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '오승현', 15, 'ehtjsdlfqhd@google.com/', '010-1234-6868', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 201, 1);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '오우진', 15, 'owj7182@google.com/', '010-1234-6869', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 251, 51);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '유정호', 15, 'ryujeongho@google.com/', '010-1234-6870', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 301, 101);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '이민정', 15, 'snailmin@google.com/', '010-1234-6871', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 351, 151);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '이재준', 15, 'jjaejun@google.com', '010-1234-6872', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 301, 1);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '임초임', 15, 'dlachdla@google.com/', '010-1234-6873', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 51, 51);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '정승범', 15, 'seungbeom4382@google.com/', '010-1234-6874', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 101, 101);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '정용준', 15, 'tior931108@google.com/', '010-1234-6875', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 151, 151);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '정진우', 15, 'jinwoo2361@google.com', '010-1234-6876', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 201, 1);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '천무진', 15, 'chunmujin@google.com/', '010-1234-6877', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 251, 51);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '한보경', 15, 'bokyunghan@google.com/', '010-1234-6878', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 301, 101);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '한승훈', 15, 'nlgz@google.com/', '010-1234-6879', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 351, 151);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '한준희', 15, 'hjh705@google.com', '010-1234-6880', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 51, 1);

-- project
INSERT INTO PROJECT (ID, TITLE, CREATED_AT, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, '사내 메신저 개발', TIMESTAMP '2024-01-05 00:00:00', TIMESTAMP '2024-04-25 00:00:00', NULL, 'ING', 351);
INSERT INTO PROJECT (ID, TITLE, CREATED_AT, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, '사내카페 앱오더 개발', TIMESTAMP '2024-01-30 00:00:00', TIMESTAMP '2024-04-30 00:00:00', NULL, 'ING', 651);
INSERT INTO PROJECT (ID, TITLE, CREATED_AT, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, 'BEMS 개발 과제', TIMESTAMP '2023-12-01 00:00:00', TIMESTAMP '2024-02-01 00:00:00', NULL, 'DONE', 801);
INSERT INTO PROJECT (ID, TITLE, CREATED_AT, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, '채용 홈페이지 UI 개선', TIMESTAMP '2023-12-15 00:00:00', TIMESTAMP '2024-02-01 00:00:00', NULL, 'DONE', 151);
INSERT INTO PROJECT (ID, TITLE, CREATED_AT, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, '취미활동 예약결재 플랫폼', TIMESTAMP '2023-12-12 00:00:00', TIMESTAMP '2024-04-12 00:00:00', NULL, 'ING', 401);
INSERT INTO PROJECT (ID, TITLE, CREATED_AT, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, 'AI 소개팅 어플', TIMESTAMP '2023-12-27 00:00:00', TIMESTAMP '2024-06-30 00:00:00', NULL, 'ING', 251);

-- prject_employee
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 101, 1, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 101, 51, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 101, 101, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 101, 151, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 101, 201, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 101, 251, 'READ');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 151, 301, 'READ');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 151, 351, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 151, 401, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 151, 451, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 201, 501, 'READ');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 201, 551, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 201, 601, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 251, 651, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 251, 701, 'READ');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 251, 751, 'READ');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 301, 801, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 301, 1252, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 351, 1302, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 351, 151, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 351, 201, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 351, 351, 'READ');


-- schedule_category
insert into schedule_category (id, color, name, emp_id) values 
(seq_schedule_category_id.nextval, 'Red', 'Meeting', 51);
insert into schedule_category (id, color, name, emp_id) values 
(seq_schedule_category_id.nextval, 'Blue', 'Workshop', 101);
insert into schedule_category (id, color, name, emp_id) values 
(seq_schedule_category_id.nextval, 'Green', 'Training', 201);
insert into schedule_category (id, color, name, emp_id) values 
(seq_schedule_category_id.nextval, 'Yellow', 'Presentation', 251);
insert into schedule_category (id, color, name, emp_id) values 
(seq_schedule_category_id.nextval, 'Purple', 'Project', 801);

select * from schedule;


--schedule
insert into schedule (id, title, content, start_time, end_time, emp_id, schedule_category_id) values 
(seq_schedule_id.nextval, 'Team Meeting', 'Discuss project updates', timestamp '2024-02-07 09:00:00', timestamp '2024-02-07 10:00:00', 51, 1);

insert into schedule (id, title, content, start_time, end_time, emp_id, schedule_category_id) values 
(seq_schedule_id.nextval, 'Training Session', 'New technology training', timestamp '2024-02-07 14:00:00', timestamp '2024-02-07 16:00:00', 101, 201);

insert into schedule (id, title, content, start_time, end_time, emp_id, schedule_category_id) values 
(seq_schedule_id.nextval, 'Project Kickoff', 'Start of a new project', timestamp '2024-02-08 10:00:00', timestamp '2024-02-08 13:00:00', 51, 1);

insert into schedule (id, title, content, start_time, end_time, emp_id, schedule_category_id) values 
(seq_schedule_id.nextval, 'Client Presentation', 'Presenting to a new client', timestamp '2024-02-09 15:00:00', timestamp '2024-02-09 16:00:00', 251, 151);

insert into schedule (id, title, content, start_time, end_time, emp_id, schedule_category_id) values 
(seq_schedule_id.nextval, 'Team Workshop', 'Brainstorming session', timestamp '2024-02-10 11:00:00', timestamp '2024-02-10 13:00:00', 801, 201);

--schedule_join_member
insert into schedule_join_member (id, schedule_id, emp_id) values 
(seq_schedule_join_member.nextval, 501, 51);
insert into schedule_join_member (id, schedule_id, emp_id) values 
(seq_schedule_join_member.nextval, 501, 101);
insert into schedule_join_member (id, schedule_id, emp_id) values 
(seq_schedule_join_member.nextval, 501, 201);
insert into schedule_join_member (id, schedule_id, emp_id) values 
(seq_schedule_join_member.nextval, 151, 251);
insert into schedule_join_member (id, schedule_id, emp_id) values 
(seq_schedule_join_member.nextval, 151, 801);


-- authority
insert into authority values (seq_authority_id.nextval, 1, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 51, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 101, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 151, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 201, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 251, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 301, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 351, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 401, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 451, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 501, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 551, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 601, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 651, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 701, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 751, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 801, 'ROLE_EMP');