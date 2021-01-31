package me.henrique.lotteryzup.domain.service;

import me.henrique.lotteryzup.domain.entity.LotteryNumbers;

import java.util.List;

public interface LotteryNumbersService {

    LotteryNumbers generateNumbers(Integer quantity, String email);

    List<LotteryNumbers> getByEmail(String email);
}
