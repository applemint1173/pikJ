package com.midproject.pikJ.service;

import com.midproject.pikJ.dto.NoticeDTO;
import com.midproject.pikJ.dto.ProgramDTO;
import com.midproject.pikJ.entity.Notice;
import com.midproject.pikJ.entity.Program;
import com.midproject.pikJ.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository repository;
    private final ModelMapper modelMapper;

    private final String ATTACHMENT_PATH = "src/main/resources/static/userImg/";

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

    //김태준 추가
    public Page<ProgramDTO> getSelectAll(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("no")));
        Page<Program> entityPage = repository.findAll(pageable);
        return entityPage.map(entity -> modelMapper.map(entity, ProgramDTO.class));
    }
    //김태준 추가
    public Page<ProgramDTO> getSelectAll(String type, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("no")));
        Page<Program> entityPage = repository.findByType(type, pageable);
        return entityPage.map(entity -> modelMapper.map(entity, ProgramDTO.class));
    }

    // 박지영
    public Page<ProgramDTO> getSelectAll(int page, String searchType, String keyword) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("no")));
        Page<Program> pageList;

        boolean noKeyword = (searchType == null || searchType.isEmpty()) &&
                (keyword == null || keyword.isEmpty());
        boolean noSearch = (searchType == null || searchType.isEmpty()) &&
                (keyword != null);

        if (noKeyword) {
            pageList = repository.findAll(pageable);
        } else if (noSearch) {
            pageList = repository.findBySubjectContainingOrContentContainingOrStageContainingOrTypeContaining(
                    keyword, keyword, keyword, keyword, pageable);
        } else {
            switch (searchType) {
                case "subject":
                    pageList = repository.findBySubjectContaining(keyword, pageable);
                    break;
                case "content":
                    pageList = repository.findByContentContaining(keyword, pageable);
                    break;
                case "stage":
                    pageList = repository.findByStageContaining(keyword, pageable);
                    break;
                case "type":
                    pageList = repository.findByTypeContaining(keyword, pageable);
                    break;
                default:
                    pageList = repository.findAll(pageable);
            }
        }

        return pageList.map(program -> modelMapper.map(program, ProgramDTO.class));
    }

    //김태준 추가, 0702 사용자 페이지 수정하기 위해 pageSize 수정
    public Page<ProgramDTO> getSelectByTypeAndStage(ProgramDTO programDTO, int page) {
        String type = programDTO.getType();
        String stage = programDTO.getStage();
        Pageable pageable = PageRequest.of(page, 9, Sort.by(Sort.Order.desc("no")));
        Page<Program> entityPage;

        if (stage == null || stage.isEmpty()) {
            entityPage = repository.findByType(type, pageable);
        } else {
            entityPage = repository.findByTypeAndStage(type, stage, pageable);
        }

        return entityPage.map(program -> modelMapper.map(program, ProgramDTO.class));
    }

    public ProgramDTO getSelectOne(ProgramDTO programDTO) {
        Optional<Program> op = repository.findById(programDTO.getNo());

        if (op.isEmpty()) {
            return null;
        }

        Program program = op.get();

        return modelMapper.map(program, ProgramDTO.class);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateStage() {
        List<Program> list = repository.findAll();
        LocalDate today = LocalDate.now();

        for(Program program : list) {
            String stage;

            if(today.isBefore(program.getStartDate().toLocalDate())) {
                stage = "진행예정";
            }

            else if(today.isAfter(program.getEndDate().toLocalDate())) {
                stage = "종료";
            }

            else {
                stage = "진행중";
            }

            if(!stage.equals(program.getStage())) {
                program.setStage(stage);
                repository.save(program);
            }

        }

        System.out.println("변경 완료" + today);
    }




    public void setInsert(ProgramDTO programDTO) throws IOException {
        MultipartFile multiPhoto = programDTO.getAttachmentFile();

        if (multiPhoto != null && !multiPhoto.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + multiPhoto.getOriginalFilename();
            Path photoPath = Paths.get(ATTACHMENT_PATH);

            if (!Files.exists(photoPath)) {
                Files.createDirectories(photoPath);
            }

            Path filePath = photoPath.resolve(fileName);
            Files.copy(multiPhoto.getInputStream(), filePath);

            programDTO.setAttachment("/userImg/" + fileName);
        } else {
            programDTO.setAttachment(null);
        }

        repository.save(modelMapper.map(programDTO, Program.class));
    }

    public void setUpdate(ProgramDTO programDTO) throws IOException {
        MultipartFile multiPhoto = programDTO.getAttachmentFile();

        if (multiPhoto != null && !multiPhoto.isEmpty()) {
            if (programDTO.getAttachment() != null) {
                String oldFileName = programDTO.getAttachment().replace("/userImg/", "");
                Path oldFilePath = Paths.get(ATTACHMENT_PATH + oldFileName);

                if (Files.exists(oldFilePath)) {
                    Files.delete(oldFilePath);
                }
            }

            String fileName = UUID.randomUUID() + "_" + multiPhoto.getOriginalFilename();
            Path photoPath = Paths.get(ATTACHMENT_PATH);

            if (!Files.exists(photoPath)) {
                Files.createDirectories(photoPath);
            }

            Path filePath = photoPath.resolve(fileName);
            Files.copy(multiPhoto.getInputStream(), filePath);

            programDTO.setAttachment("/userImg/" + fileName);
        } else {
            programDTO.setAttachment(null);
        }

        repository.save(modelMapper.map(programDTO, Program.class));
    }

    public void setDelete(ProgramDTO programDTO) throws IOException {
        if (programDTO.getAttachment() != null) {
            String oldFileName = programDTO.getAttachment().replace("/userImg/", "");
            Path oldFilePath = Paths.get(ATTACHMENT_PATH + oldFileName);

            if (Files.exists(oldFilePath)) {
                Files.delete(oldFilePath);
            }
        }

        repository.delete(modelMapper.map(programDTO, Program.class));
    }

}
