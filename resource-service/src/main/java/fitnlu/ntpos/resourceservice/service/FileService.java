package fitnlu.ntpos.resourceservice.service;

import fitnlu.ntpos.resourceservice.repository.FileRepository;
import fitnlu.ntpos.resourceservice.dto.FileUploadResponse;
import fitnlu.ntpos.resourceservice.entities.FileEntities;
import fitnlu.ntpos.resourceservice.util.FileUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    private FileRepository imageRepository;
    private final static String IMAGE_URL = "/api/file/image/";
    public FileUploadResponse uploadImage(MultipartFile file) throws IOException {
        String id = UUID.randomUUID().toString();
        byte[] bytes = file.getBytes();
        imageRepository.save(FileEntities.builder()
                .id(id)
                .data(FileUtil.compressImage(bytes))
                .size(file.getSize())
                .type(file.getContentType())
                .build());
        return FileUploadResponse.builder()
                .id(id)
                .size(file.getSize())
                .url(IMAGE_URL+id)
                .type(file.getContentType())
                .build();
    }

    @Transactional
    public FileUploadResponse getInfoByImageByID(String id) {
        Optional<FileEntities> dbImage = imageRepository.findById(id);
        return FileUploadResponse.builder()
                .id(dbImage.get().getId())
                .type(dbImage.get().getType())
                .size(dbImage.get().getSize())
                .url(IMAGE_URL+id)
                .build();

    }
    @Transactional
    public byte[] getImage(String id) {
        Optional<FileEntities> dbImage = imageRepository.findById(id);
        return FileUtil.decompressImage(dbImage.get().getData());
    }

    public List<FileUploadResponse> getAll(){
        return imageRepository.findAll().stream().map(fileEntities -> FileUploadResponse.builder()
                .size(fileEntities.getSize())
                .url(IMAGE_URL+fileEntities.getId())
                .type(fileEntities.getType())
                .id(fileEntities.getId())
                .build()).toList();
    }
    public void deleteAll(){
        imageRepository.deleteAll();
    }
}
