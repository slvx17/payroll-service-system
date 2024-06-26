package com.lawencon.pss_app.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.pss_app.dto.fileops.FileReqDto;
import com.lawencon.pss_app.dto.fileops.FileResDto;
import com.lawencon.pss_app.dto.fileops.GetFileReqDto;
import com.lawencon.pss_app.dto.fileops.GetFileResDto;
import com.lawencon.pss_app.model.Document;
import com.lawencon.pss_app.model.File;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.repo.UserRepo;
import com.lawencon.pss_app.service.SftpService;

    @RestController
    public class FileController {

        @Autowired
        private SftpService sftpService;

        @Autowired
        private UserRepo userRepo;

        @PostMapping("/upload")
        public ResponseEntity<FileResDto> uploadFile(
                @RequestParam("file") MultipartFile file,
                @RequestParam("email") String email,
                @RequestParam("dateId") Long dateId) {  // Added dateId parameter
            try {
                User user = userRepo.findByEmail(email);
                if (user == null) {
                    return ResponseEntity.badRequest().body(new FileResDto("Invalid username", false));
                }
                
                String directoryPath = System.getProperty("user.home") + "/temp/"; 
                Files.createDirectories(Paths.get(directoryPath));
                
                String localFilePath = directoryPath + file.getOriginalFilename();
                file.transferTo(new java.io.File(localFilePath));
                String remoteFilePath = System.getProperty("user.home") + "/remote/path/" + file.getOriginalFilename();

                // Assuming you would do something with the dateId here, such as logging or storing it in the database
                sftpService.uploadFile(localFilePath, remoteFilePath, user, dateId);  // Pass dateId to your service if needed

                return ResponseEntity.ok(new FileResDto("File uploaded successfully", true));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(new FileResDto("Failed to upload file: " + e.getMessage(), false));
            }
        }


    
    @PostMapping("/getdocuments")
    public ResponseEntity<GetFileResDto> getDocuments(@RequestBody GetFileReqDto getFileDto) {
        try {
            List<Document> documents = sftpService.getDocs(getFileDto);
            List<Long> ids = new ArrayList<>();
            List<String> names = new ArrayList<>();
            for(Document doc: documents) {
            	ids.add(doc.getId());
            	names.add(doc.getFile().getFileName()+"."+doc.getFile().getFileExt());
            }
            
            if (documents.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(new GetFileResDto(ids, names));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    
    @PostMapping("/download")
    public ResponseEntity<FileResDto> downloadFile(@RequestBody FileReqDto fileReqDto) {
        try {
            User user = userRepo.findByUsername(fileReqDto.getUsername());
            if (user == null) {
                return ResponseEntity.badRequest().body(new FileResDto("Invalid username", false));
            }
            
            String localPath = System.getProperty("user.home") + "/downloads/" + new java.io.File(fileReqDto.getFilePath()).getName();
            File downloadedFile = sftpService.downloadFile(fileReqDto.getFilePath(), localPath, user);
            
            if (downloadedFile != null) {
                return ResponseEntity.ok(new FileResDto("File downloaded successfully and database updated.", true));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new FileResDto("Error during file download: " + e.getMessage(), false));
        }
    }


}
