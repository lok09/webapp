package com.example.SpringMVC.service;

import com.example.SpringMVC.dao.LectureRepository;
import com.example.SpringMVC.dao.MaterialRepository;
import com.example.SpringMVC.exception.LectureNotFindException;
import com.example.SpringMVC.model.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    private MaterialRepository materialRepository;
    private LectureRepository lectureRepository;

    @Autowired
    public void setMaterialRepository(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Autowired
    public void setLectureRepository(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void addMaterial(Long id, List<MultipartFile> attachments, Principal principal)
            throws LectureNotFindException, IOException {
        List<Material> materialList = new ArrayList<>();
        for(MultipartFile file: attachments){
            Material material = new Material();
            material.setOwner_username(principal.getName());
            material.setLecture(lectureRepository.findById(id).orElseThrow(LectureNotFindException::new));
            material.setMaterialName(file.getOriginalFilename());
            material.setContents(file.getBytes());
            material.setMimeContentType(file.getContentType());
            material.setDate(new Date());
            materialList.add(material);
        }
        if(materialList.size() == 1){
            materialRepository.save(materialList.get(0));
        }else {
            materialRepository.saveAll(materialList);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void deleteMaterialById(Long id){
        materialRepository.deleteById(id);
    }

    @Transactional
    public Optional<Material> findMaterialById(Long id){
        return materialRepository.findById(id);
    }
}
