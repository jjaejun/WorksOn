package com.sh.workson.position.service;

import com.sh.workson.position.entity.Position;
import com.sh.workson.position.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;
    public List<Position> findAll() {
        List<Position> positions = positionRepository.findAll();
        return positions;
    }
}
