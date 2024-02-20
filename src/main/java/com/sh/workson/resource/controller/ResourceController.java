package com.sh.workson.resource.controller;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.attachment.entity.AttachType;
import com.sh.workson.attachment.service.S3FileService;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.reservation.dto.ReservationListDto;
import com.sh.workson.reservation.dto.ReservationReturnDto;
import com.sh.workson.reservation.entity.Reservation;
import com.sh.workson.reservation.service.ReservationService;
import com.sh.workson.resource.dto.ResourceCreateDto;
import com.sh.workson.resource.entity.Resource;
import com.sh.workson.resource.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private S3FileService s3FileService;

    @GetMapping("/createResource.do")
    public void createResource() {}

    @PostMapping("/createResource.do")
    public String createResource(
            ResourceCreateDto resourceCreateDto,
            @RequestParam(name = "files") List<MultipartFile> files,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            RedirectAttributes redirectAttributes)
            throws IOException {
        log.debug("files = {}", files);
        log.debug("projectCreateDto = {}", resourceCreateDto);

        // 첨부파일 S3에 저장
        for(MultipartFile file : files) {
            log.debug("file = {}", file);
            if(file.getSize() > 0){
                AttachmentCreateDto attachmentCreateDto = s3FileService.upload(file, AttachType.RESOURCE);
                attachmentCreateDto.setEmployee(employeeDetails.getEmployee());
                log.debug("attachmentCreateDto = {}", attachmentCreateDto);
                resourceCreateDto.addAttachmentCreateDto(attachmentCreateDto);
            }
        }
        resourceService.createResource(resourceCreateDto);

        return "redirect:/reservation/reservation" + resourceCreateDto.getType() + ".do";
    }

    @GetMapping("/deleteResource.do")
    public void deleteResource(Model model) {

        List<Resource> resources = resourceService.findByOrderByType();
        log.debug("resources = {}", resources);

        model.addAttribute("resources", resources);
    }

    @GetMapping("/reservationListForResource.do")
    public ResponseEntity<?> reservationListForResource(@RequestParam Long id) {
        log.debug("id = {}", id);
        LocalDateTime today = LocalDateTime.now();
        log.debug("today = {}", today);

        List<Reservation> reservations = reservationService.findAllAfterToday(id, today);
        log.debug("reservations = {}", reservations);
        List<ReservationReturnDto> reservationReturnDtos = new ArrayList<>();
        reservations.forEach(reservation ->
                reservationReturnDtos.add(ReservationReturnDto.builder()
                                .id(reservation.getId())
                                .empName(reservation.getEmployee().getName())
                                .count(reservation.getCount())
                                .startAt(reservation.getStartAt())
                                .endAt(reservation.getEndAt())
                                .content(reservation.getContent())
                                .resourceId(reservation.getResourceId())
                        .build()));
        log.debug("reservationReturnDtos = {}", reservationReturnDtos);

        return new ResponseEntity<>(reservationReturnDtos, HttpStatus.OK);
    }

    @PostMapping("/deleteResource.do")
    public String deleteResource(@RequestParam Long resourceList) {
        log.debug("resourceList = {}", resourceList);

        resourceService.deleteById(resourceList);
        return "redirect:/reservation/reservationRoom.do";
    }
}
