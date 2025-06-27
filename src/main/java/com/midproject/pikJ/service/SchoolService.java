// 박지영
package com.midproject.pikJ.service;

import com.midproject.pikJ.dto.ProgramDTO;
import com.midproject.pikJ.dto.SchoolDTO;
import com.midproject.pikJ.entity.Program;
import com.midproject.pikJ.entity.School;
import com.midproject.pikJ.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<SchoolDTO> getSelectAll(int page, String searchType, String keyword) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("no")));
        Page<School> pageList;

        boolean noKeyword = (searchType == null || searchType.isEmpty()) &&
                (keyword == null || keyword.isEmpty());
        boolean noSearch = (searchType == null || searchType.isEmpty()) &&
                (keyword != null);

        if (noKeyword) {
            pageList = repository.findAll(pageable);
        } else if (noSearch) {
            pageList = repository.findByNameContainingOrSortContainingOrLocationContainingOrPhoneContaining(
                    keyword, keyword, keyword, keyword, pageable);
        } else {
            switch (searchType) {
                case "name":
                    pageList = repository.findByNameContaining(keyword, pageable);
                    break;
                case "sort":
                    pageList = repository.findBySortContaining(keyword, pageable);
                    break;
                case "location":
                    pageList = repository.findByLocationContaining(keyword, pageable);
                    break;
                case "phone":
                    pageList = repository.findByPhoneContaining(keyword, pageable);
                    break;
                default:
                    pageList = repository.findAll(pageable);
            }
        }

        return pageList.map(school -> modelMapper.map(school, SchoolDTO.class));
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
