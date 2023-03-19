package com.noobs.gazonuz.dtos.upload;

import org.springframework.web.multipart.MultipartFile;
import com.noobs.gazonuz.dtos.DTO;

public class DocumentCreateDTO implements DTO{
    private MultipartFile file;

    public DocumentCreateDTO(MultipartFile file) {
        this.file = file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }
}

