package me.henrique.lotteryzup.domain.service;

import me.henrique.lotteryzup.domain.entity.PlayNumbers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface NumberService {

    PlayNumbers generateNumbers(Integer quantity, String email);

    List<PlayNumbers> getByEmail(String email);
}
