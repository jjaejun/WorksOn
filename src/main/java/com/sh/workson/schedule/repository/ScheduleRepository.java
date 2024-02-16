package com.sh.workson.schedule.repository;

import com.sh.workson.schedule.dto.ScheduleListDto;
import com.sh.workson.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("from Schedule s left join fetch s.scheduleCategory sc where s.scheduleCategory.id = :categoryId")
    List<Schedule> findByCategoryId(Long categoryId);
}
