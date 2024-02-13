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
}
