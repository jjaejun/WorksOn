package com.sh.workson.approval.repository;

import com.sh.workson.approval.entity.Approval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    from
        employee e join approval a
            on e.id = a.emp_id
         join approval_leave eq
            on a.approval_type_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = 251
""", nativeQuery = true)
    Page<Approval> findAll(Pageable pageable);
}
