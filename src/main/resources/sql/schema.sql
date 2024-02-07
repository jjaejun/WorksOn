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


-- 민정

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


-- 재준

-- table drop
-- drop table chat_read;
-- drop table chat_log;
-- drop table chat_emp;
-- drop table chat_room;
-- drop table tb_resource;
-- drop table reservation;

-- sequence drop
-- drop sequence seq_chat_log_id;
-- drop sequence seq_chat_room_id;
-- drop sequence seq_tb_resource_id;
-- drop sequence seq_reservation_id;

-- 채팅방
create table chat_room (
    id number not null
    , name varchar2(255) not null
    , constraint pk_chat_room_id primary key(id)
);
create sequence seq_chat_room_id start with 1 increment by 50;

-- 채팅 인원
create table chat_emp (
    chat_room_id number not null
    , employee_id number not null
    , constraint pk_chat_emp_chat_room_id primary key(chat_room_id)
    , constraint fk_chat_emp_chat_room_id foreign key(chat_room_id) references chat_room(id) on delete cascade
    , constraint fk_chat_emp_employee_id foreign key(employee_id) references employee(id) on delete cascade
);

-- 채팅 내역
create table chat_log(
    id number not null
    , content varchar2(900)
    , created_at timestamp default systimestamp
    , employee_id number not null
    , chat_room_id number not null
    , constraint pk_chat_log_id primary key(id)
    , constraint fk_chat_log_employee_id foreign key(employee_id) references employee(id)
    , constraint fk_chat_log_chat_room_id foreign key(chat_room_id) references chat_room(id) on delete cascade
);
create sequence seq_chat_log_id start with 1 increment by 50;

-- 채팅 읽지않음 확인
create table chat_read(
    chat_log_id number not null
    , employee_id number not null
    , constraint pk_chat_read_chat_log_id primary key(chat_log_id)
    , constraint fk_chat_read_chat_log_id foreign key(chat_log_id) references chat_log(id) on delete cascade
    , constraint fk_chat_read_employee_id foreign key(employee_id) references employee(id) on delete cascade
);

-- 자원
create table tb_resource(
    id number not null
    , name varchar2(255)
    , location varchar2(900)
    , info varchar2(900)
    , type varchar2(10)
    , constraint pk_tb_resource_id primary key(id)
    , constraint uq_tb_resource_name unique(name)
);
create sequence seq_tb_resource_id start with 1 increment by 50;

-- 예약
create table reservation(
    id number not null
    , start_at timestamp not null
    , end_at timestamp not null
    , content varchar(900) not null
    , count number not null
    , created_at timestamp default systimestamp
    , emp_id number not null
    , tb_resource_id number not null
    , constraint pk_reservation_id primary key(id)
    , constraint fk_reservation_emp_id foreign key(emp_id) references employee(id) on delete cascade
    , constraint fk_reservation_tb_resource_id foreign key(tb_resource_id) references tb_resource(id) on delete cascade
);
create sequence seq_reservation_id start with 1 increment by 50;

-- 우진

-- table drop

-- drop table approval_leave;
-- drop table approval_equipment;
-- drop table approval_cooperation;
-- drop table approval_attachment;
-- drop table approval_line;
-- drop table approval;

-- sequence drop

-- drop sequence seq_approval_form_id;
-- drop sequence seq_approval_id;
-- drop sequence seq_approval_attachment_id;
-- drop sequence seq_approval_line_id;

-- 결재 연차 테이블
create table approval_leave (
    id number not null
    , name varchar2(20) default '연차 신청' not null
    , title varchar2(100) not null
    , start_date timestamp not null
    , end_date timestamp not null
    , leave_content varchar2(500) 
    , created_at timestamp default systimestamp
    , constraints pk_approval_leave_id primary key(id)
);
-- 결재 양식 관련 ex 연차/비품/협조 같은 시퀀스 사용예정 각 시퀀스 사용시 조인시 겹칠 우려 있어서 원하는 값이 조회 안될듯
create sequence seq_approval_form_id start with 1 increment by 50; 

-- 결재 비품신청 테이블
create table approval_equipment (
    id number not null
    , name varchar2(20) default '비품 신청' not null
    , title varchar2(100) not null
    , content varchar2(2000)
    , product_name varchar2(50) not null
    , usage varchar2(100)
    , price number not null
    , count number not null
    , equipment_date timestamp default systimestamp
    , constraints pk_approval_equipment_id primary key(id)
);


-- 결재 협조 테이블
create table approval_cooperation (
    id number not null
    , name varchar2(20) default '협조 신청' not null
    , title varchar2(100) not null
    , content varchar2(2000)
    , cooperation_dept varchar2(50) not null
    , start_date timestamp not null
    , end_date timestamp not null
    , people number not null
    , created_at timestamp default systimestamp
    , constraints pk_approval_cooperation_id primary key(id)
);


-- 결재 테이블 테이블
create table approval (
    id number not null
    , emp_id number not null
    , approval_type_id number
    , approval_start_date timestamp default systimestamp
    , approval_end_date timestamp
    , emergency char(1) default 'N'
    , status varchar2(20) default '대기' not null
    , constraints pk_approval_id primary key(id)
    , constraints fk_employee_id foreign key(emp_id) references employee(id) on delete set null
    , constraints ck_approval_emergency check (emergency in ('Y', 'N'))
    , constraints ck_approval_status check (status in ('대기', '진행중', '임시저장', '승인', '반려'))
);
create sequence seq_approval_id start with 1 increment by 50;

-- 전자결재 첨부파일 테이블
create table approval_attachment (
    id number not null
    , approval_id number not null
    , path varchar2(100) not null
    , renamed_filename varchar2(200) not null
    , original_filename varchar2(200) not null
    , type varchar2(30) not null
    , created_at timestamp default systimestamp
    , constraints pk_approval_attachment_id primary key(id)
    , constraints fk_approval_id foreign key(approval_id) references approval(id) on delete set null
);
create sequence seq_approval_attachment_id start with 1 increment by 50;


-- 결재라인 테이블
create table approval_line (
    id number not null
    , approval_id number not null
    , approver_id number not null
    , rejection varchar2(200)
    , confirm_date timestamp default systimestamp
    , status varchar2(20) default '진행'  not null
    , constraints pk_approval_line_id primary key(id)
    , constraints fk_approval_line_approval_id foreign key(approval_id) references approval(id) on delete set null
    , constraints ck_approval_line_status check (status in ('대기', '진행중', '예정', '승인', '반려'))
);
create sequence seq_approval_line_id start with 1 increment by 50;

-- 무진

-- table drop

-- sequence drop

-- 준희

-- table drop

-- sequence drop

-- 민준

-- table drop

-- sequence drop
