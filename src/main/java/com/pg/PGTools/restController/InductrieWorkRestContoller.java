package com.pg.PGTools.restController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pg.PGTools.entity.InductrieWork;
import com.pg.PGTools.service.InductrieWorkService;

@RestController
@RequestMapping("/api")
public class InductrieWorkRestContoller {

	@Autowired
    private InductrieWorkService inductrieWorkService;

//    private final String uploadDir = "src/main/resources/static/images/InductriesImages";
	private final String uploadDir = "/home/ubuntu/project/images/InductriesImages";

//  Save Inductries
    @PostMapping("/add-inductrie")
    public ResponseEntity<String> addInductrie(@RequestParam("name") String name,
                                               @RequestParam("image") MultipartFile imageFile) {
        try {
            // Save file
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Save to DB
            InductrieWork inductrie = new InductrieWork();
            inductrie.setName(name);
            inductrie.setImage(fileName); // just filename or path

            inductrieWorkService.save(inductrie);

            return ResponseEntity.ok("Saved successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }
    
//  Get All Inductries
    @GetMapping("/inductries")
    public List<InductrieWork> getAllInductries() {
        return inductrieWorkService.getAllInductries();
    }
    
    @DeleteMapping("/delete-inductrie/{id}")
    public ResponseEntity<String> deleteInductrie(@PathVariable Long id) {
        boolean deleted = inductrieWorkService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inductrie not found");
        }
    }

}
