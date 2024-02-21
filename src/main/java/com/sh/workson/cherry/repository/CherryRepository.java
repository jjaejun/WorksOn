package com.sh.workson.cherry.repository;

import com.sh.workson.cherry.entity.Cherry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CherryRepository extends JpaRepository<Cherry, Long> {
}
