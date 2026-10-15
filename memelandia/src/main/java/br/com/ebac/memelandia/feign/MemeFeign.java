package br.com.ebac.memelandia.feign;

import br.com.ebac.memelandia.dto.MemeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "meme", url = "${meme.service.url}")
public interface MemeFeign {
   @PostMapping("/meme/{categoryID}")
   void createMeme(@RequestBody MemeDTO meme, @PathVariable(name = "categoryID") Long categoryID);

   @PutMapping("/meme/{memeID}")
   void updateMeme(@RequestBody MemeDTO meme, @PathVariable(name = "memeID") Long memID);

   @DeleteMapping("/meme/{memeID}/{userID}")
   void deleteMeme(@PathVariable(name = "memeID") Long memID, @PathVariable(name = "userID") Long userID);

   @GetMapping("/meme")
   List<MemeDTO> listMeme();
}
