package com.midproject.pikJ.service;

import com.midproject.pikJ.dto.ProgramDTO;
import com.midproject.pikJ.entity.Program;
import com.midproject.pikJ.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository repository;
    private final ModelMapper modelMapper;

    public List<ProgramDTO> getSelectAll() {
        List<Program> entityList = repository.findAll();
        List<ProgramDTO> dtoList = new ArrayList<>();

        for (int i = 0; i < entityList.size(); i ++) {
            dtoList.add(modelMapper.map(entityList.get(i), ProgramDTO.class));
        }

        return dtoList;
    }

    public List<ProgramDTO> getSelectAll(String type) {
        List<Program> entityList = repository.findByType(type);
        List<ProgramDTO> dtoList = new ArrayList<>();

        for (int i = 0; i < entityList.size(); i ++) {
            dtoList.add(modelMapper.map(entityList.get(i), ProgramDTO.class));
        }

        return dtoList;
    }

    public ProgramDTO getSelectOne(ProgramDTO programDTO) {
        Optional<Program> op = repository.findById(programDTO.getNo());

        if (op.isEmpty()) {
            return null;
        }

        Program program = op.get();

        return modelMapper.map(program, ProgramDTO.class);
    }

    public void setInsert(ProgramDTO programDTO) {
        repository.save(modelMapper.map(programDTO, Program.class));
    }

    public void setUpdate(ProgramDTO programDTO) {
        repository.save(modelMapper.map(programDTO, Program.class));
    }

    public void setDelete(ProgramDTO programDTO) {
        repository.delete(modelMapper.map(programDTO, Program.class));
    }

}
