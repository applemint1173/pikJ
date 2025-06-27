//정재백
package com.midproject.pikJ.service;

import com.midproject.pikJ.dto.NoticeDTO;
import com.midproject.pikJ.entity.Notice;
import com.midproject.pikJ.repository.NoticeRepository;
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

    //김태준
    public Page<Notice>getSelectAll(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("no"));
        Pageable pageable = PageRequest.of(page,10,Sort.by(sorts));
        return repository.findAll(pageable);
    }

    public NoticeDTO getSelectOne(NoticeDTO noticeDTO) {
        Optional<Notice> op = repository.findById(noticeDTO.getNo());

        if (!op.isPresent()) {
            return null;
        }

        Notice notice = op.get();
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
