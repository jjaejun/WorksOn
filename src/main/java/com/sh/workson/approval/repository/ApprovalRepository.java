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
    //        a.approval_start_date
//        , a.approval_end_date
//        , a.approval_type_id
//        , a.emp_id
    //        , eq.name
//        , eq.title
//        , a.emergency
//        , at.id
//        , a.status
//    , eq.*
//            , at.*
    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id
         join approval_form frm
            on a.approval_form_id = frm.id  
         join approval_leave le
            on frm.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = 251
""", nativeQuery = true)
    Page<Approval> findAllLeave(Pageable pageable);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id
         join approval_form frm
            on a.approval_form_id = frm.id   
         join approval_equipment eq
            on frm.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = 251
""", nativeQuery = true)
    Page<Approval> findAllEquipment(Pageable pageable);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id
         join approval_form frm
            on a.approval_form_id = frm.id   
         join approval_cooperation co
            on frm.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = 251
""", nativeQuery = true)
    Page<Approval> findAllCooperation(Pageable pageable);


//    @Query(value = """
//    SELECT al.name, al.title
//    FROM approval_leave al join approval a
//        on al.id = a.approval_type_id
//    WHERE a.id = 251
//""", nativeQuery = true)
//    List<ApprovalLeave> findApprovalLeavesByApprovalId(@Param("approvalId") Long approvalId);

//    @Query("SELECT al FROM ApprovalLeave al WHERE al.id = :approvalTypeId")
//    List<ApprovalLeave> findApprovalLeavesByApprovalId(@Param("approvalTypeId") Long approvalTypeId);


}
