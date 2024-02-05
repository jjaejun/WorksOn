-- DDL 작성

-- table drop
-- drop table department cascade constraints;
-- drop table employee cascade constraints;
-- drop table position cascade constraints;
-- drop table project cascade constraints;
-- drop table project_employee cascade constraints;
-- drop table board cascade constraints;
-- drop table board_comment cascade constraints;
-- drop table attachment cascade constraints;
-- drop table authority cascade constraints;
-- drop table task cascade constraints;
-- drop table issue cascade constraints;
-- drop table project_comment cascade constraints;

-- sequence drop
-- drop sequence seq_employee_id;
-- drop sequence seq_department_id;
-- drop sequence seq_position_id;
-- drop sequence seq_project_id;
-- drop sequence seq_project_employee_id;
-- drop sequence seq_board_id;
-- drop sequence seq_board_comment_id;
-- drop sequence seq_attachment_id;
-- drop sequence seq_authority_id;
-- drop sequence seq_task_id;
-- drop sequence seq_issue_id;
-- drop sequence seq_project_comment_id;


-- 부서
create table department (
        id number not null,
        name varchar2(255) not null,
        constraints pk_department_id primary key(id)
);
create sequence seq_department_id start with 1 increment by 50;

-- 직급
create table position (
        id number not null,
        name varchar2(255) not null,
        position_power number,
        constraints pk_position_id primary key(id)
);
create sequence seq_position_id start with 1 increment by 50;

-- 사원
create table employee (
        id number not null,
        password varchar2(1000) not null,
        name varchar2(255 char) not null,
        rest number default 0,
        email varchar2(255) not null,
        phone varchar2(255 char) not null,
        birthday date,
        created_at timestamp,
        updated_at timestamp,
        work_status varchar2(30),
        profile_original_name varchar2(1000),
        profile_key varchar2(1000),
        profile_url varchar2(1000),
        seed number,
        cherry number,
        position_id number,
        dept_id number,
        constraints pk_employee_id primary key(id),
        constraints fk_employee_position_id foreign key(position_id) references position(id) on delete set null,
        constraints fk_employee_dept_id foreign key(dept_id) references department(id) on delete set null,
        constraints uq_employee_email unique(email),
        constraints uq_employee_phone unique(phone),
        constraints ck_employee_work_status check (work_status in ('WORK', 'QUIT'))
);
create sequence seq_employee_id start with 1 increment by 50;

-- 권한
create table authority (
    id number,
    emp_id number not null,
    name varchar2(50) not null,
    constraints pk_authority_id primary key(id),
    constraints fk_authority_emp_id foreign key(emp_id) references employee(id) on delete cascade,
    constraints uq_authority_emp_id_name unique (emp_id, name)
);
create sequence seq_authority_id start with 1 increment by 50;

-- 프로젝트
create table project (
    id number not null,
    title varchar2(2000) not null,
    created_at timestamp,
    end_at timestamp,
    updated_at timestamp,
    status varchar2(100),
    owner_id number,
    constraints pk_project_id primary key(id),
    constraints fk_project_owner_id foreign key(owner_id) references employee(id) on delete set null
);
create sequence seq_project_id start with 1 increment by 50;

-- 프로젝트 참여사원
create table project_employee (
    id number not null,
    project_id number,
    emp_id number,
    project_role varchar2(30),
    constraints pk_project_employee_id primary key(id),
    constraints fk_project_employee_project_id foreign key(project_id) references project(id) on delete set null,
    constraints fk_project_employee_emp_id foreign key(emp_id) references employee(id) on delete set null,
    constraints ck_project_employee_project_role check (project_role in ('READ', 'CREATE'))
);
create sequence seq_project_employee_id start with 1 increment by 50;

-- 프로젝트 첨부파일 - attachment 같이 사용

-- 프로젝트 댓글
create table project_comment (
    id number not null,
    content varchar2(4000) not null,
    parent_comment_id number,
    comment_level number default 1,
    created_at timestamp not null,
    emp_id number,
    project_id number not null,
    type varchar2(10),
    constraints pk_project_comment_id primary key(id),
    constraint fk_project_comment_parent_id foreign key (parent_comment_id) references project_comment(id) on delete cascade,
    constraints fk_project_comment_emp_id foreign key(emp_id) references employee(id) on delete set null,
    constraints fk_project_comment_project_id foreign key(project_id) references project(id) on delete set null,
    constraints ck_project_comment_type check (type in ('TASK', 'ISSUE'))
);
create sequence seq_project_comment_id start with 1 increment by 50;

-- 프로젝트 업무
create table task (
    id number not null,
    name varchar2(1000) not null,
    content varchar2(4000),
    priority number default 1,
    start_at timestamp not null,
    end_at timestamp,
    status varchar2(30),
    owner_id number,
    emp_id number,
    project_id number,
    constraints pk_task_id primary key(id),
    constraint fk_task_owner_id foreign key (owner_id) references employee(id) on delete set null,
    constraint fk_task_emp_id foreign key (emp_id) references employee(id) on delete set null,
    constraint fk_task_project_id foreign key (project_id) references project(id) on delete cascade
);
create sequence seq_task_id start with 1 increment by 50;

-- 프로젝트 이슈
create table issue (
    id number not null,
    name varchar2(1000) not null,
    content varchar2(4000),
    priority number default 1,
    status varchar2(30),
    owner_id number,
    emp_id number,
    project_id number,
    task_id number,
    constraints pk_issue_id primary key(id),
    constraint fk_issue_owner_id foreign key (owner_id) references employee(id) on delete set null,
    constraint fk_issue_emp_id foreign key (emp_id) references employee(id) on delete set null,
    constraint fk_issue_project_id foreign key (project_id) references project(id) on delete cascade,
    constraint fk_issue_task_id foreign key (task_id) references task(id) on delete cascade
);
create sequence seq_issue_id start with 1 increment by 50;

--게시판
create table board (
   id number not null,
   type varchar2(30) not null,
   title varchar2(100) not null,
   content varchar2(4000) not null,
   view_count number default 0,
   created_at timestamp default systimestamp,
   updated_at timestamp default systimestamp,
   emp_id number, -- fk on delete set null일 경우, not null이면 안되서 고쳤습니다~
   constraint pk_board_id primary key (id),
   constraint fk_board_emp_id foreign key (emp_id) references employee(id) on delete set null
);
create sequence seq_board_id start with 1 increment by 50;

--댓글
create table board_comment (
    id number not null,
    board_id number not null,
    content varchar2(4000) not null,
    parent_comment_id number, -- 댓글인 경우 null, 대댓글인 경우 부모댓글id
    comment_level number default 1, -- 댓글인 경우 1, 대댓글인 경우 2
    created_at timestamp default systimestamp,
    updated_at timestamp default systimestamp,
    emp_id number,
    constraint pk_comment_id primary key (id),
    constraint fk_comment_board_id foreign key (board_id) references board(id) on delete cascade,
    constraint fk_comment_employee_id foreign key (emp_id) references employee(id) on delete set null,
    constraint fk_comment_parent_id foreign key (parent_comment_id) references board_comment(id) on delete cascade
);
create sequence seq_board_comment_id start with 1 increment by 50;

--첨부파일
create table attachment (
    id number,
    board_type varchar2(50) not null,
    board_id number not null,
    original_file_name varchar(255) not null,
    key varchar2(1000) not null, 
    url varchar(1000) not null,
    created_at timestamp default systimestamp,
    constraint pk_attachment_id primary key (id)
);
create sequence seq_attachment_id start with 1 increment by 50;


