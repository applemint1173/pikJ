// 박지영
package com.midproject.pikJ.service;

import com.midproject.pikJ.dto.SchoolDTO;
import com.midproject.pikJ.entity.School;
import com.midproject.pikJ.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository repository;
    private final ModelMapper modelMapper;

    public List<SchoolDTO> getSelectAll() {
        List<School> entityList = repository.findAll();
        List<SchoolDTO> dtoList = new ArrayList<>();

        for (int i = 0; i < entityList.size(); i ++) {
            dtoList.add(modelMapper.map(entityList.get(i), SchoolDTO.class));
        }

        return dtoList;
    }

    public SchoolDTO getSelectOne(SchoolDTO schoolDTO) {
        Optional<School> os = repository.findById(schoolDTO.getNo());

        if (!os.isPresent()) {
            return null;
        }

        School school = os.get();
        return modelMapper.map(school, SchoolDTO.class);
    }

    public void setInsert(SchoolDTO schoolDTO) {
        repository.save(modelMapper.map(schoolDTO, School.class));
    }

    public void setUpdate(SchoolDTO schoolDTO) {
        repository.save(modelMapper.map(schoolDTO, School.class));
    }

    public void setDelete(SchoolDTO schoolDTO) {
        repository.delete(modelMapper.map(schoolDTO, School.class));
    }

}
