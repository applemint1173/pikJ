//정재백
package com.midproject.pikJ.service;

import com.midproject.pikJ.dto.NoticeDTO;
import com.midproject.pikJ.entity.Notice;
import com.midproject.pikJ.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
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

    public Page<NoticeDTO> getSelectAll(int page, String searchType, String keyword) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("no")));
        Page<Notice> pageList;

        boolean noKeyword = (searchType == null || searchType.isEmpty()) &&
                (keyword == null || keyword.isEmpty());
        boolean noSearch = (searchType == null || searchType.isEmpty()) &&
                (keyword != null);

        if (noKeyword) {
            pageList = repository.findAll(pageable);
        } else if (noSearch) {
            pageList = repository.findBySubjectContainingOrWriterContainingOrContentContaining(
                    keyword, keyword, keyword, pageable);
        } else {
            switch (searchType) {
                case "subject":
                    pageList = repository.findBySubjectContaining(keyword, pageable);
                    break;
                case "writer":
                    pageList = repository.findByWriterContaining(keyword, pageable);
                    break;
                case "content":
                    pageList = repository.findByContentContaining(keyword, pageable);
                    break;
                default:
                    pageList = repository.findAll(pageable);
            }
        }

        return pageList.map(notice -> modelMapper.map(notice, NoticeDTO.class));
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
