package com.sh.workson.reservation.controller;

import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.attachment.service.AttachmentService;
import com.sh.workson.attend.entity.AttendListDto;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.reservation.dto.ReservationCreateDto;
import com.sh.workson.reservation.dto.ReservationListDto;
import com.sh.workson.reservation.entity.Reservation;
import com.sh.workson.reservation.service.ReservationService;
import com.sh.workson.resource.entity.Resource;
import com.sh.workson.resource.entity.Type;
import com.sh.workson.resource.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/reservation")
public class ReservationContoller {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private AttachmentService attachmentService;

    @GetMapping("/reservationRoom.do")
    public void reservationRoom(Model model) {
        List<Resource> resources = resourceService.findByType(Type.Room);
        log.debug("resources = {}", resources);
        List<Attachment> attachments = attachmentService.find

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
        model.addAttribute("resourceId", id);
        model.addAttribute("reservations", reservations);
    }

    @PostMapping("/createReservation.do")
    public String createReservation(ReservationCreateDto reservationCreateDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        log.debug("reservationCreateDto = {}", reservationCreateDto);
        reservationService.createReservation(reservationCreateDto);
        redirectAttributes.addFlashAttribute("msg", "예약 신청이 완료되었습니다.😎");
        return "redirect:reservationMyList.do";
    }

    @GetMapping("/reservationMyList.do")
    public void reservationMyDetail(@AuthenticationPrincipal EmployeeDetails employeeDetails, Model model) {
        List<Reservation> reservations = reservationService.findByEmpId(employeeDetails.getEmployee().getId());
        log.debug("reservations = {}", reservations);
        model.addAttribute("reservations", reservations);
    }

    @PostMapping("/deleteReservation.do")
    public String deleteReservation(@RequestParam("reservationId") Long reservationId, RedirectAttributes redirectAttributes) {
        log.debug("reservationId = {}", reservationId);
        reservationService.deleteById(reservationId);
        redirectAttributes.addFlashAttribute("msg", "예약이 취소되었습니다.");
        return "redirect:reservationMyList.do";
    }

    @GetMapping("/reservationListSearchDate.do")
    public ResponseEntity<?> attendListSearchDate(
            Model model,
            @RequestParam("resourceId") Long resourceId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @PageableDefault(value = 5, page = 0) Pageable pageable
    ){
        log.debug("resourceId = {}", resourceId);
        log.debug("startDate = {}", startDate);
        log.debug("endDate = {}", endDate);
        LocalDateTime startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(startDate)), ZoneId.systemDefault());
        LocalDateTime endTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(endDate)), ZoneId.systemDefault());

//        LocalDateTime startTime = LocalDateTime.parse(startDate.replace("T", "") + " 00:00", DateTimeFormatter.ofPattern("yy/MM/dd HH:mm"));
//        LocalDateTime endTime = LocalDateTime.parse(endDate.replace("T", "") + " 23:59", DateTimeFormatter.ofPattern("yy/MM/dd HH:mm"));

        // endTime에 1일을 더함
        endTime = endTime.plusDays(1);

        log.debug("T startTime = {}", startTime); // T startDate = 24/02/07 00:00
        log.debug("T endTime = {}", endTime); // T endDate = 24/02/10 23:59

        Page<ReservationListDto> reservationPage = reservationService.findBetweenSearchDatePage(pageable, resourceId, startTime, endTime);
        return ResponseEntity.ok(reservationPage);
    }

    @GetMapping("/reservationCheck.do")
    public ResponseEntity<?> reservationCheck(
            @RequestParam("startAt") LocalDateTime startAt,
            @RequestParam("endAt") LocalDateTime endAt,
            @RequestParam("resourceId") Long resourceId
    ) {
        log.debug("startAt = {}", startAt);
        log.debug("endAt = {}", endAt);
        log.debug("resourceId = {}", resourceId);

        int reservationCheckNum = reservationService.findBetweenSearchDate(resourceId, startAt, endAt);
        log.debug("reservationCheckNum = {}", reservationCheckNum);
        return ResponseEntity.ok(reservationCheckNum);
    }
}
