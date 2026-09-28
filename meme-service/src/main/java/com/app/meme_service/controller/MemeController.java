package com.app.meme_service.controller;

import com.app.meme_service.dto.MemeDTO;
import com.app.meme_service.service.MemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/meme")
public class MemeController {
    @Autowired
    private MemeService memeService;

    @PostMapping("/{categoryID}")
    public ResponseEntity<MemeDTO> createMeme(@RequestBody MemeDTO memeDTO, @PathVariable(name = "categoryID") Long categoryID) {
        try{
            return ResponseEntity.ok(memeService.createMeme(memeDTO, categoryID));
        } catch (Exception e) {
            log.error("POST - /meme - createMeme - memeDTO {}", memeDTO);
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
