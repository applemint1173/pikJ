// 김태준
package com.midproject.pikJ.service;

import com.midproject.pikJ.dto.MemberDTO;
import com.midproject.pikJ.dto.ProgramDTO;
import com.midproject.pikJ.entity.Member;
import com.midproject.pikJ.entity.Program;
import com.midproject.pikJ.repository.MemberRepository;
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
public class MemberService {

    private final MemberRepository repository;
    private final ModelMapper modelMapper;

    public List<MemberDTO> getSelectAll() {
        List<Member> entityList = repository.findAll();
        List<MemberDTO> dtoList = new ArrayList<>();
        for (int i = 0; i < entityList.size(); i++) {
            dtoList.add(modelMapper.map(entityList.get(i), MemberDTO.class));
        }
        return dtoList;
    }

    public Page<MemberDTO> getSelectAll(int page, String searchType, String keyword) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("no")));
        Page<Member> pageList;

        boolean noKeyword = (searchType == null || searchType.isEmpty()) &&
                (keyword == null || keyword.isEmpty());
        boolean noSearch = (searchType == null || searchType.isEmpty()) &&
                (keyword != null);

        if (noKeyword) {
            pageList = repository.findAll(pageable);
        } else if (noSearch) {
            pageList = repository.findByNameContainingOrPhoneContainingOrTypeContaining(
                    keyword, keyword, keyword, pageable);
        } else {
            switch (searchType) {
                case "name":
                    pageList = repository.findByNameContaining(keyword, pageable);
                    break;
                case "phone":
                    pageList = repository.findByPhoneContaining(keyword, pageable);
                    break;
                case "type":
                    pageList = repository.findByTypeContaining(keyword, pageable);
                    break;
                default:
                    pageList = repository.findAll(pageable);
            }
        }

        return pageList.map(member -> modelMapper.map(member, MemberDTO.class));
    }

    public MemberDTO getSelectOne(MemberDTO memberDTO) {
        MemberDTO returnDTO = new MemberDTO();
        Optional<Member> om = repository.findById(memberDTO.getNo());

        if (om.isEmpty()) {
            returnDTO = null;
        }

        Member member = om.get();
        returnDTO = modelMapper.map(member, MemberDTO.class);

        return returnDTO;
    }

    // 박지영 : 아이디통해 DTO 반환
    public MemberDTO getSelectLoginOne(MemberDTO memberDTO) {
        Optional<Member> om = repository.findById(memberDTO.getId());

        if (!om.isPresent()) {
            return null;
        }

        Member member = om.get();
        return modelMapper.map(member, MemberDTO.class);
    }

    public void setInsert(MemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        repository.save(member);
    }

    public void setUpdate(MemberDTO memberDTO) {
        repository.save(modelMapper.map(memberDTO, Member.class));
    }

    public void setDelete(MemberDTO memberDTO) {
        repository.delete(modelMapper.map(memberDTO, Member.class));
    }
}
