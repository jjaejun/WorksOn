package com.sh.workson.reservation.service;

import com.sh.workson.employee.entity.Employee;
import com.sh.workson.reservation.dto.ReservationCreateDto;
import com.sh.workson.reservation.entity.Reservation;
import com.sh.workson.reservation.repository.ReservationRepository;
import com.sh.workson.resource.entity.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
