package com.sh.workson.reservation.service;

import com.sh.workson.reservation.entity.Reservation;
import com.sh.workson.reservation.repository.ReservationRepository;
import com.sh.workson.resource.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> findByResourceId(Long id) {
        return reservationRepository.findByResourceId(id);
    }
}
