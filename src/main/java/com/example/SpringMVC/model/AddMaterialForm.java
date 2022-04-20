package com.example.SpringMVC.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

public class AddMaterialForm implements Serializable {
    private List<MultipartFile> attachments;

    public List<MultipartFile> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<MultipartFile> attachments) {
        this.attachments = attachments;
    }
}
