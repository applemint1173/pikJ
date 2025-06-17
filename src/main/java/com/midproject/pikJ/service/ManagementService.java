// 박지영
package com.midproject.pikJ.service;

import com.midproject.pikJ.dto.ManagementDTO;
import com.midproject.pikJ.entity.Counselor;
import com.midproject.pikJ.entity.Management;
import com.midproject.pikJ.entity.Member;
import com.midproject.pikJ.entity.School;
import com.midproject.pikJ.repository.CounselorRepository;
import com.midproject.pikJ.repository.ManagementRepository;
import com.midproject.pikJ.repository.MemberRepository;
import com.midproject.pikJ.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagementService {

    private final ManagementRepository repository;
    private final MemberRepository memberRepository;
    private final CounselorRepository counselorRepository;
    private final SchoolRepository schoolRepository;
    private final ModelMapper modelMapper;

    public List<ManagementDTO> getSelectAll() {
        List<Management> entityList = repository.findAll();
        List<ManagementDTO> dtoList = new ArrayList<>();

        for (int i = 0; i < entityList.size(); i ++) {
            dtoList.add(modelMapper.map(entityList.get(i), ManagementDTO.class));
        }

        return dtoList;
    }

    public ManagementDTO getSelectOne(ManagementDTO managementDTO) {
        Optional<Management> om = repository.findById(managementDTO.getNo());

        if (!om.isPresent()) {
            return null;
        }

        Management management = om.get();
        return modelMapper.map(management, ManagementDTO.class);
    }

    public void setInsert(ManagementDTO managementDTO) {
        //managementDTO.setPassword(passwordEncoder.encode(managementDTO.getPassword()));
        Optional<Member> om = memberRepository.findById(managementDTO.getMember_no());
        Optional<Counselor> oc = counselorRepository.findById(managementDTO.getCounselor_no());
        Optional<School> os = schoolRepository.findById(managementDTO.getSchool_no());

        // 추후에 수정 : 세개가 1개라도 존재하지 않으면 isEmpty 활용해서 실패값으로 반환
        if (om.isPresent() && oc.isPresent() && os.isPresent()) {
            Member member = om.get();
            Counselor counselor = oc.get();
            School school = os.get();

            managementDTO.setMember(member);
            managementDTO.setCounselor(counselor);
            managementDTO.setSchool(school);

            repository.save(modelMapper.map(managementDTO, Management.class));
        }
    }

    public void setUpdate(ManagementDTO managementDTO) {
        //managementDTO.setPassword(passwordEncoder.encode(managementDTO.getPassword()));
        Optional<Member> om = memberRepository.findById(managementDTO.getMember_no());
        Optional<Counselor> oc = counselorRepository.findById(managementDTO.getCounselor_no());
        Optional<School> os = schoolRepository.findById(managementDTO.getSchool_no());

        // 추후에 수정 : 세개가 1개라도 존재하지 않으면 isEmpty 활용해서 실패값으로 반환
        if (om.isPresent() && oc.isPresent() && os.isPresent()) {
            Member member = om.get();
            Counselor counselor = oc.get();
            School school = os.get();

            managementDTO.setMember(member);
            managementDTO.setCounselor(counselor);
            managementDTO.setSchool(school);
            repository.save(modelMapper.map(managementDTO, Management.class));
        }
    }

    public void setDelete(ManagementDTO managementDTO) {
        repository.delete(modelMapper.map(managementDTO, Management.class));
    }

}