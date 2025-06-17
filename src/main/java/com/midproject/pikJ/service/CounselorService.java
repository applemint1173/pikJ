package com.midproject.pikJ.service;

import com.midproject.pikJ.dto.CounselorDTO;
import com.midproject.pikJ.entity.Counselor;
import com.midproject.pikJ.repository.CounselorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CounselorService {

    private final CounselorRepository repository;
    private final ModelMapper modelMapper;

    public List<CounselorDTO> getSelectAll() {
        List<Counselor> entityList = repository.findAll();
        List<CounselorDTO> dtoList = new ArrayList<>();
        for (int i = 0; i < entityList.size(); i++) {
            dtoList.add(modelMapper.map(entityList.get(i), CounselorDTO.class));
        }
        return dtoList;
    }

    public CounselorDTO getSelectOne(CounselorDTO counselorDTO) {

        Optional<Counselor> om = repository.findById(counselorDTO.getNo());

        if (!om.isPresent()) {
            return null;
        }

        Counselor counselor = om.get();
        return modelMapper.map(counselor, CounselorDTO.class);
    }

    public void setInsert(CounselorDTO counselorDTO) {
        Counselor counselor = modelMapper.map(counselorDTO, Counselor.class);
        repository.save(counselor);
    }

    public void setUpdate(CounselorDTO counselorDTO) {
        repository.save(modelMapper.map(counselorDTO, Counselor.class));
    }

    public void setDelete(CounselorDTO counselorDTO) {
        repository.delete(modelMapper.map(counselorDTO, Counselor.class));
    }
}
