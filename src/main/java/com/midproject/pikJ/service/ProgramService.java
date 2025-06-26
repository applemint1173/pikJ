package com.midproject.pikJ.service;

import com.midproject.pikJ.dto.ProgramDTO;
import com.midproject.pikJ.entity.Program;
import com.midproject.pikJ.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    public ProgramDTO getSelectOne(ProgramDTO programDTO) {
        Optional<Program> op = repository.findById(programDTO.getNo());

        if (op.isEmpty()) {
            return null;
        }

        Program program = op.get();

        return modelMapper.map(program, ProgramDTO.class);
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

    public void setDelete(ProgramDTO programDTO) {
        repository.delete(modelMapper.map(programDTO, Program.class));
    }

}
