package com.sh.workson.reservation.controller;

import com.sh.workson.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReservationContoller {

    @Autowired
    private ReservationService reservationService;
}
