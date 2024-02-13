package com.sh.workson.approval.repository;

import com.sh.workson.approval.dto.ApprovalHomeLeaveDto;
import com.sh.workson.approval.entity.Approval;
import com.sh.workson.approval.entity.ApprovalLeave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id  
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id
""", nativeQuery = true)
    Page<Approval> findAllLeave(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id
""", nativeQuery = true)
    Page<Approval> findAllEquipment(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id
""", nativeQuery = true)
    Page<Approval> findAllCooperation(Long id, Pageable pageable);


    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status = '임시저장'
""", nativeQuery = true)
    Page<Approval> findTempoeraryLeave(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status = '임시저장'
""", nativeQuery = true)
    Page<Approval> findTemporaryEquipment(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status = '임시저장'
""", nativeQuery = true)
    Page<Approval> findTemporaryCooperation(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id
""", nativeQuery = true)
    Page<Approval> findReLeave(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id
""", nativeQuery = true)
    Page<Approval> findReEquipment(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id
""", nativeQuery = true)
    Page<Approval> findReCooperation(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status in ('승인', '반려')
""", nativeQuery = true)
    Page<Approval> findCheckLeave(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status in ('승인', '반려')
""", nativeQuery = true)
    Page<Approval> findCheckEquipment(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status in ('승인', '반려')
""", nativeQuery = true)
    Page<Approval> findCheckCooperation(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status in ('진행중')
""", nativeQuery = true)
    Page<Approval> findProceedingLeave(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status in ('진행중')
""", nativeQuery = true)
    Page<Approval> findProceedingEquipment(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status in ('진행중')
""", nativeQuery = true)
    Page<Approval> findProceedingCooperation(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id
        and a.status in ('대기', '진행중')
""", nativeQuery = true)
    Page<Approval> findReceiveLeave(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id
        and a.status in ('대기', '진행중')
""", nativeQuery = true)
    Page<Approval> findReceiveEquipment(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id
        and a.status in ('대기', '진행중')
""", nativeQuery = true)
    Page<Approval> findReceiveCooperation(Long id, Pageable pageable);

    @Query(value = """
    select
         a. *
         , le.name
         , le.title
         , d.name
     from
         employee e join approval a
            on e.id = a.emp_id
        join department d
            on e.dept_id = d.id
        join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
 where
     e.dept_id = :deptId
     and a.emp_id not in :id
     and a.status not in ('임시저장')
""", nativeQuery = true)
    Page<Approval> findReceptionLeave(Long deptId, Long id, Pageable pageable);

    @Query(value = """
    select
         a. *
         , eq.name
         , eq.title
         , d.name
     from
         employee e join approval a
            on e.id = a.emp_id
        join department d
            on e.dept_id = d.id
        join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
 where
     e.dept_id = :deptId
     and a.emp_id not in :id
     and a.status not in ('임시저장')
""", nativeQuery = true)
    Page<Approval> findReceptionEquipment(Long deptId, Long id, Pageable pageable);

    @Query(value = """
    select
         a. *
         , co.name
         , co.title
         , d.name
     from
         employee e join approval a
            on e.id = a.emp_id
        join department d
            on e.dept_id = d.id
        join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
 where
     e.dept_id = :deptId
     and a.emp_id not in :id
     and a.status not in ('임시저장')
""", nativeQuery = true)
    Page<Approval> findReceptionCooperation(Long deptId, Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id and
        a.status = '대기'
""", nativeQuery = true)
    Page<Approval> findWaitLeave(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id and
        a.status = '대기'
""", nativeQuery = true)
    Page<Approval> findWaitEquipment(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id and
        a.status = '대기'
""", nativeQuery = true)
    Page<Approval> findWaitCooperation(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id and
        a.status = '예정'
""", nativeQuery = true)
    Page<Approval> findExceptedLeave(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id and
        a.status = '예정'
""", nativeQuery = true)
    Page<Approval> findExceptedEquipment(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id and
        a.status = '예정'
""", nativeQuery = true)
    Page<Approval> findExceptedCooperation(Long id, Pageable pageable);
}
