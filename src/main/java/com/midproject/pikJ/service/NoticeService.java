package com.midproject.pikJ.service;

import com.midproject.pikJ.dto.NoticeDTO;
import com.midproject.pikJ.entity.Notice;
import com.midproject.pikJ.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository repository;
    private final ModelMapper modelMapper;

    public List<NoticeDTO> getSelectAll() {
        List<Notice> entityList = repository.findAll();
        List<NoticeDTO> dtoList = new ArrayList<>();

        for (int i = 0; i < entityList.size(); i ++) {
            dtoList.add(modelMapper.map(entityList.get(i), NoticeDTO.class));
        }

        return dtoList;
    }

    public NoticeDTO getSelectOne(NoticeDTO noticeDTO) {
        Optional<Notice> on = repository.findById(noticeDTO.getNo());

        if (!on.isPresent()) {
            return null;
        }

        Notice notice = on.get();
        return modelMapper.map(notice, NoticeDTO.class);
    }

    public void setInsert(NoticeDTO noticeDTO) {
        repository.save(modelMapper.map(noticeDTO, Notice.class));
    }

    public void setUpdate(NoticeDTO noticeDTO) {
        repository.save(modelMapper.map(noticeDTO, Notice.class));
    }

    public void setDelete(NoticeDTO noticeDTO) {
        repository.delete(modelMapper.map(noticeDTO, Notice.class));
    }

}
