package com.app.meme_service.controller;

import com.app.meme_service.dto.MemeDTO;
import com.app.meme_service.entity.MemeEntity;
import com.app.meme_service.service.MemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/meme")
public class MemeController {
    @Autowired
    private MemeService memeService;

    @PostMapping("/create/{categoryID}")
    private ResponseEntity<MemeDTO> createMeme(@RequestBody MemeDTO meme, @PathVariable Long categoryID) {
        return ResponseEntity.ok(memeService.createMeme(meme, categoryID));
    }

    @PutMapping("/update/{memeID}")
    private ResponseEntity<MemeDTO> updateMeme(@RequestBody MemeDTO meme,@PathVariable Long memeID) {
        return ResponseEntity.ok(memeService.updateMeme(meme, memeID));
    }

    @DeleteMapping("/delete/{memeID}")
    private ResponseEntity<Void> deleteMeme(@PathVariable Long memeID) {
        memeService.deleteMeme(memeID);
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    private ResponseEntity<List<MemeEntity>> listMeme() {
        List<MemeEntity> memes = memeService.listMeme();

        return ResponseEntity.ok(memes);
    }
}
