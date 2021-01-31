package me.henrique.lotteryzup.api.resources;

import me.henrique.lotteryzup.api.dto.PersonDTO;
import me.henrique.lotteryzup.api.dto.PlayNumbersDTO;
import me.henrique.lotteryzup.domain.entity.PlayNumbers;
import me.henrique.lotteryzup.domain.service.NumberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/playnumbers")
public class PlayNumbersController {

    @Autowired
    private NumberService service;

    @PostMapping("/{quantityNumbers}")
    public ResponseEntity<PlayNumbersDTO> create(@PathVariable Integer quantityNumbers, @Valid @RequestBody PersonDTO dto) {
        PlayNumbers playNumbers = service.generateNumbers(quantityNumbers, dto.getEmail());
        return ResponseEntity.ok().body(new PlayNumbersDTO(playNumbers));
    }

    @GetMapping
    public ResponseEntity<List<PlayNumbersDTO>> getByEmail(@RequestBody PersonDTO dto) {
        List<PlayNumbers> listNumbers = service.getByEmail(dto.getEmail());
        List<PlayNumbersDTO> listDTO = listNumbers.stream().map(x -> new PlayNumbersDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
