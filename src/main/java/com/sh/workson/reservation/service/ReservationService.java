package com.sh.workson.reservation.service;

import com.sh.workson.employee.entity.Employee;
import com.sh.workson.reservation.dto.ReservationCreateDto;
import com.sh.workson.reservation.dto.ReservationListDto;
import com.sh.workson.reservation.dto.ReservationReturnDto;
import com.sh.workson.reservation.entity.Reservation;
import com.sh.workson.reservation.repository.ReservationRepository;
import com.sh.workson.resource.entity.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ModelMapper modelMapperStrict;

    public List<Reservation> findByResourceId(Long id) {
        return reservationRepository.findByResourceId(id);
    }

    public void createReservation(ReservationCreateDto reservationCreateDto) {
        reservationRepository.save(convertToReservation(reservationCreateDto));
    }

    private Reservation convertToReservation(ReservationCreateDto reservationCreateDto) {
        Reservation reservation = modelMapperStrict.map(reservationCreateDto, Reservation.class);
        reservation.setEmployee(Employee.builder().id(reservationCreateDto.getEmployeeId()).build());
        reservation.setResourceId(reservationCreateDto.getResourceId());
        return reservation;
    }


    public List<Reservation> findByEmpId(Long id) {
        return reservationRepository.findByEmpId(id);
    }

    public void deleteById(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    public Page<ReservationListDto> findBetweenSearchDatePage(Pageable pageable, Long resourceId, LocalDateTime startTime, LocalDateTime endTime) {
        Page<Reservation> reservationPage = reservationRepository.findBetweenSearchDatePage(pageable, resourceId, startTime, endTime);
        return reservationPage.map((reservation -> convertToReservationListDto(reservation)));
    }

    private ReservationListDto convertToReservationListDto(Reservation reservation) {
        ReservationListDto reservationListDto = ReservationListDto.builder()
                .startAt(reservation.getStartAt())
                .endAt(reservation.getEndAt())
                .content(reservation.getContent())
                .count(reservation.getCount())
                .empName(reservation.getEmployee().getName())
                .resourceId(reservation.getResourceId())
                .build();
        return reservationListDto;
    }

    public int findBetweenSearchDate(Long resourceId, LocalDateTime startAt, LocalDateTime endAt) {
        return reservationRepository.findBetweenSearchDate(resourceId, startAt, endAt);
    }

    public List<Reservation> findAllAfterToday(Long id, LocalDateTime today) {
        return reservationRepository.findAllAfterToday(id, today);
    }
}
