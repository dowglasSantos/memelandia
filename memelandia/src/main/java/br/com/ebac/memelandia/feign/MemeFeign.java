package br.com.ebac.memelandia.feign;

import br.com.ebac.memelandia.dto.MemeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "meme", url = "http://localhost:8082/meme")
public interface MemeFeign {
   @PostMapping("/{categoryID}/{userID}")
   void createMeme(@RequestBody MemeDTO meme, @PathVariable(name = "categoryID") Long categoryID, @PathVariable(name = "userID") Long userID);

   @PutMapping("/{memeID}/{userID}")
   void updateMeme(@RequestBody MemeDTO meme, @PathVariable(name = "memeID") Long memID, @PathVariable(name = "userID") Long userID);

   @DeleteMapping("/{memeID}/{userID}")
   void deleteMeme(@PathVariable(name = "memeID") Long memID, @PathVariable(name = "userID") Long userID);

   @GetMapping()
   List<MemeDTO> listMeme();
}
