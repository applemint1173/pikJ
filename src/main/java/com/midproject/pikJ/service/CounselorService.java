// 김태준
package com.midproject.pikJ.service;

import com.midproject.pikJ.dto.CounselorDTO;
import com.midproject.pikJ.dto.ProgramDTO;
import com.midproject.pikJ.entity.Counselor;
import com.midproject.pikJ.entity.Program;
import com.midproject.pikJ.repository.CounselorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CounselorService {

    private final CounselorRepository repository;
    private final ModelMapper modelMapper;

    private final String PHOTO_PATH = "src/main/resources/static/userImg/";

    public List<CounselorDTO> getSelectAll() {
        List<Counselor> entityList = repository.findAll();
        List<CounselorDTO> dtoList = new ArrayList<>();
        for (int i = 0; i < entityList.size(); i++) {
            dtoList.add(modelMapper.map(entityList.get(i), CounselorDTO.class));
        }
        return dtoList;
    }

    public Page<CounselorDTO> getSelectAll(int page, String searchType, String keyword) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("no")));
        Page<Counselor> pageList;

        boolean noKeyword = (searchType == null || searchType.isEmpty()) &&
                (keyword == null || keyword.isEmpty());
        boolean noSearch = (searchType == null || searchType.isEmpty()) &&
                (keyword != null);

        if (noKeyword) {
            pageList = repository.findAll(pageable);
        } else if (noSearch) {
            pageList = repository.findByNameContainingOrPhoneContaining(
                    keyword, keyword, pageable);
        } else {
            switch (searchType) {
                case "name":
                    pageList = repository.findByNameContaining(keyword, pageable);
                    break;
                case "phone":
                    pageList = repository.findByPhoneContaining(keyword, pageable);
                    break;
                default:
                    pageList = repository.findAll(pageable);
            }
        }

        return pageList.map(counselor -> modelMapper.map(counselor, CounselorDTO.class));
    }

    public CounselorDTO getSelectOne(CounselorDTO counselorDTO) {
        Optional<Counselor> om = repository.findById(counselorDTO.getNo());

        if (!om.isPresent()) {
            return null;
        }

        Counselor counselor = om.get();
        return modelMapper.map(counselor, CounselorDTO.class);
    }

    // 박지영 : 아이디통해 DTO 반환
    public CounselorDTO getSelectLoginOne(CounselorDTO counselorDTO) {
        CounselorDTO returnDTO = new CounselorDTO();
        Optional<Counselor> oc = repository.findById(counselorDTO.getId());

        if (oc.isEmpty()) {
            returnDTO = null;
        }

        Counselor counselor = oc.get();
        returnDTO =  modelMapper.map(counselor, CounselorDTO.class);

        return returnDTO;
    }

    public void setInsert(CounselorDTO counselorDTO) throws IOException {
//        MultipartFile multiPhoto = counselorDTO.getPhotoFile();
//
//        if (multiPhoto != null && !multiPhoto.isEmpty()) {
//            String fileName = UUID.randomUUID() + "_" + multiPhoto.getOriginalFilename();
//            Path photoPath = Paths.get(PHOTO_PATH);
//
//            if (!Files.exists(photoPath)) {
//                Files.createDirectories(photoPath);
//            }
//
//            Path filePath = photoPath.resolve(fileName);
//            Files.copy(multiPhoto.getInputStream(), filePath);
//
//            counselorDTO.setPhoto("/userImg/" + fileName); // 뷰 사진 src에 다이렉트로 경로 두기 위함
//        } else {
//            counselorDTO.setPhoto(null);
//        }

        Counselor entity = modelMapper.map(counselorDTO, Counselor.class);
        repository.save(entity);
    }

    public void setUpdate(CounselorDTO counselorDTO) throws IOException {
//        MultipartFile multiPhoto = counselorDTO.getPhotoFile();
//
//        if (multiPhoto != null && !multiPhoto.isEmpty()) {
//            if (counselorDTO.getPhoto() != null) {
//                String oldFileName = counselorDTO.getPhoto().replace("/userImg/", "");
//                Path oldFilePath = Paths.get(PHOTO_PATH + oldFileName);
//
//                if (Files.exists(oldFilePath)) {
//                    Files.delete(oldFilePath);
//                }
//            }
//
//            String fileName = UUID.randomUUID() + "_" + multiPhoto.getOriginalFilename();
//            Path photoPath = Paths.get(PHOTO_PATH);
//
//            if (!Files.exists(photoPath)) {
//                Files.createDirectories(photoPath);
//            }
//
//            Path filePath = photoPath.resolve(fileName);
//            Files.copy(multiPhoto.getInputStream(), filePath);
//
//            counselorDTO.setPhoto("/userImg/" + fileName);
//        } else {
//            counselorDTO.setPhoto(null);
//        }

        Counselor entity = modelMapper.map(counselorDTO, Counselor.class);
        repository.save(entity);
    }

    public void setDelete(CounselorDTO counselorDTO) throws IOException {
//        if (counselorDTO.getPhoto() != null) {
//            String oldFileName = counselorDTO.getPhoto().replace("/userImg/", "");
//            Path oldFilePath = Paths.get(PHOTO_PATH + oldFileName);
//
//            if (Files.exists(oldFilePath)) {
//                Files.delete(oldFilePath);
//            }
//        }

        repository.delete(modelMapper.map(counselorDTO, Counselor.class));
    }
}
