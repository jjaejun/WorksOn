package com.sh.workson.reservation.repository;

import com.sh.workson.reservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("from Reservation where resourceId = :id")
    List<Reservation> findByResourceId(Long id);

    @Query("from Reservation where employee.id = :id")
    List<Reservation> findByEmpId(Long id);

    @Query("from Reservation r where r.resourceId = :resourceId and r.startAt >= :startTime and r.endAt < :endTime order by r.startAt desc")
    Page<Reservation> findBetweenSearchDate(Pageable pageable, Long resourceId, LocalDateTime startTime, LocalDateTime endTime);
}
