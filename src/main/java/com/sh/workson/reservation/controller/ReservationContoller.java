package com.sh.workson.reservation.controller;

import com.sh.workson.reservation.dto.ReservationCreateDto;
import com.sh.workson.reservation.entity.Reservation;
import com.sh.workson.reservation.service.ReservationService;
import com.sh.workson.resource.entity.Resource;
import com.sh.workson.resource.entity.Type;
import com.sh.workson.resource.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/reservation")
public class ReservationContoller {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ResourceService resourceService;

    @GetMapping("/reservationRoom.do")
    public void reservationRoom(Model model) {
        List<Resource> resources = resourceService.findByType(Type.Room);
        log.debug("resources = {}", resources);

        model.addAttribute("resources", resources);
    }

    @GetMapping("/reservationNotebook.do")
    public void reservationNotebook(Model model) {
        List<Resource> resources = resourceService.findByType(Type.Notebook);
        log.debug("resources = {}", resources);

        model.addAttribute("resources", resources);
    }

    @GetMapping("/reservationCar.do")
    public void reservationCar(Model model) {
        List<Resource> resources = resourceService.findByType(Type.Car);
        log.debug("resources = {}", resources);

        model.addAttribute("resources", resources);
    }

    @GetMapping("/reservationList.do")
    public void reservationDetail(@RequestParam("id") Long id, Model model) {
        List<Reservation> reservations = reservationService.findByResourceId(id);
        model.addAttribute("reservations", reservations);
    }

    @PostMapping("/createReservation.do")
    public String createReservation(ReservationCreateDto reservationCreateDto) {
        log.debug("reservationCreateDto = {}", reservationCreateDto);
        return "redirect:reservationList.do";
    }
}
