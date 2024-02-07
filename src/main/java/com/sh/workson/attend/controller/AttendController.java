package com.sh.workson.attend.controller;

import com.sh.workson.attend.entity.Attend;
import com.sh.workson.attend.entity.AttendListDto;
import com.sh.workson.attend.service.AttendService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/attend")
public class AttendController {

    @Autowired
    private AttendService attendService;

    @GetMapping ("/attendList.do")
    public void attendList(@PageableDefault(value = 5, page = 0)Pageable pageable, Model model){
        Long id = 952L;
    Page<AttendListDto> attendPage = attendService.findAll(pageable, id);
    model.addAttribute("attends",attendPage.getContent());
    model.addAttribute("totalCount", attendPage.getTotalElements());
        log.debug("attends = {}", attendPage.getContent());
    }

    // 출근 버튼을 처리하는 메소드
    @PostMapping("/startWork.do")
    public String startWork(
            @Valid Attend attend,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErros = bindingResult.getAllErrors();
            for (ObjectError error : allErros) {
                log.error("{}", error);
                String fieldName = error.getObjectName();
                String message = error.getDefaultMessage();
                throw new RuntimeException("출근시간 등록 오류:" + message);
            }
        }
        attend = attendService.insertAttend(attend);
        log.debug("attends = {}", attend);
        redirectAttributes.addFlashAttribute("msg", "출근 등록이 완료 되었습니다.");
        return "redirect:/";
    }
}
