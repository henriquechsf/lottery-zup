package me.henrique.lotteryzup.api.resources;

import me.henrique.lotteryzup.api.dto.LotteryNumbersDTO;
import me.henrique.lotteryzup.api.dto.PersonDTO;
import me.henrique.lotteryzup.domain.entity.LotteryNumbers;
import me.henrique.lotteryzup.domain.service.LotteryNumbersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/numbers")
public class LotteryNumbersController {

    private ModelMapper mapper;
    private LotteryNumbersService service;

    public LotteryNumbersController(ModelMapper mapper, LotteryNumbersService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping("/{quantityNumbers}")
    @ResponseStatus(HttpStatus.CREATED)
    public LotteryNumbersDTO create(@PathVariable int quantityNumbers, @Valid @RequestBody PersonDTO dto) {
        LotteryNumbers numbers = service.generateNumbers(quantityNumbers, dto.getEmail());
        return mapper.map(numbers, LotteryNumbersDTO.class);
    }

    @GetMapping
    public List<LotteryNumbersDTO> getByEmail(@RequestBody PersonDTO dto) {
        List<LotteryNumbers> listNumbers = service.getByEmail(dto.getEmail());
        List<LotteryNumbersDTO> listDTO = listNumbers
                .stream().map(x -> mapper.map(x, LotteryNumbersDTO.class))
                .collect(Collectors.toList());
        return listDTO;
    }
}
