package me.henrique.lotteryzup.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LotteryNumbersDTO {

    private Long id;
    private String numbers;
}
