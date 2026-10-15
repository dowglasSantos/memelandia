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
    public MemeService memeService;

    @PostMapping("/{categoryID}")
    public ResponseEntity<MemeDTO> createMeme(@RequestBody MemeDTO meme, @PathVariable(name = "categoryID") Long categoryID) {
        return ResponseEntity.ok(memeService.createMeme(meme, categoryID));
    }

    @PutMapping("/{memeID}")
    public ResponseEntity<MemeDTO> updateMeme(@RequestBody MemeDTO meme,@PathVariable Long memeID) {
        return ResponseEntity.ok(memeService.updateMeme(meme, memeID));
    }

    @DeleteMapping("/{memeID}/{userID}")
    public ResponseEntity<Void> deleteMeme(@PathVariable Long memeID, @PathVariable(name = "userID") Long userID) {
        memeService.deleteMeme(memeID, userID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<MemeEntity>> listMeme() {
        List<MemeEntity> memes = memeService.listMeme();

        return ResponseEntity.ok(memes);
    }
}
