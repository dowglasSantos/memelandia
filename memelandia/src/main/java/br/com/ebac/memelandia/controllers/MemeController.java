package br.com.ebac.memelandia.controllers;

import br.com.ebac.memelandia.dto.MemeDTO;
import br.com.ebac.memelandia.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meme")
public class MemeController {
    @Autowired
    MemeService memeService;

    @PostMapping("/{categoryID}/{userID}")
    public MemeDTO createMeme(@RequestBody MemeDTO meme, @PathVariable(name = "categoryID") Long categoryID, @PathVariable(name = "userID") Long userID) {
        return memeService.createMeme(meme, categoryID, userID);
    }

    @PutMapping("/{memeID}/{userID}")
    public MemeDTO updateMeme(@RequestBody MemeDTO meme, @PathVariable(name = "memeID") Long memeID, @PathVariable(name = "userID") Long userID) {
        return memeService.updateMeme(meme, memeID, userID);
    }

    @DeleteMapping("/{memeID}/{userID}")
    public void deleteMeme(@PathVariable(name = "memeID") Long memeID, @PathVariable(name = "userID") Long userID) {
        memeService.deleteMeme(memeID, userID);
    }

    @GetMapping()
    public List<MemeDTO> listMeme() {
        return memeService.listMeme();
    }
}
