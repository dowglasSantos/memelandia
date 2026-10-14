package br.com.ebac.memelandia.service;

import br.com.ebac.memelandia.dto.MemeDTO;
import br.com.ebac.memelandia.feign.MemeFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemeService {
    @Autowired
    MemeFeign memeFeign;

    public MemeDTO createMeme(MemeDTO meme, Long categoryID, Long userID) {
        MemeDTO memeDTO = new MemeDTO(meme.getName(), meme.getDescription(), meme.getMemeUrl());

        memeFeign.createMeme(meme, categoryID, userID);

        return memeDTO;
    }

    public MemeDTO updateMeme(MemeDTO meme, Long memeID, Long userID) {
        MemeDTO memeDTO = new MemeDTO(meme.getName(), meme.getDescription(), meme.getMemeUrl());

        memeFeign.updateMeme(meme, memeID, userID);

        return memeDTO;
    }

    public void deleteMeme(Long memeID, Long userID) {
        memeFeign.deleteMeme(memeID, userID);
    }

    public List<MemeDTO> listMeme() {
        return memeFeign.listMeme();
    }
}
