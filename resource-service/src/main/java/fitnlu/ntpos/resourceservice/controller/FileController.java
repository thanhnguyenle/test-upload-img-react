package fitnlu.ntpos.resourceservice.controller;

import fitnlu.ntpos.resourceservice.dto.FileUploadResponse;
import fitnlu.ntpos.resourceservice.entities.FileEntities;
import fitnlu.ntpos.resourceservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
@CrossOrigin("*")
public class FileController {
    @Autowired
    private FileService imageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
       FileUploadResponse response = imageService.uploadImage(file);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?>  getImageInfoByID(@PathVariable("id") String id){
        FileUploadResponse image = imageService.getInfoByImageByID(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<?>  getImageByID(@PathVariable("id") String id){
        byte[] image = imageService.getImage(id);
        FileUploadResponse imageInfo = imageService.getInfoByImageByID(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(imageInfo.getType()))
                .body(image);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK)
               // .contentType(MediaType.valueOf("image/png"))
                .body(imageService.getAll());
    }

    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){
        imageService.deleteAll();
    }
}
