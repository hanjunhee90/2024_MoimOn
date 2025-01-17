package com.future.my.config;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	
	public static String saveFile(MultipartFile file, String uploadPath) {
        // 파일이 비어 있는지 체크
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("파일이 없습니다. 업로드할 파일을 선택해 주세요.");
        }

        String originalFileName = file.getOriginalFilename();
        String savedFileName = UUID.randomUUID() + "_" + originalFileName;
        File saveFile = new File(uploadPath, savedFileName);

        try {
            file.transferTo(saveFile); // 파일 저장
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.");
        }

        return savedFileName;
    }

}
