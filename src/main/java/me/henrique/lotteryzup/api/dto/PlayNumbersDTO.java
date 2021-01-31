package me.henrique.lotteryzup.api.dto;

import me.henrique.lotteryzup.domain.entity.PlayNumbers;

import javax.persistence.Column;

public class PlayNumbersDTO {

    private Long id;
    private String numbers;

    public PlayNumbersDTO() {
    }

    public PlayNumbersDTO(Long id, String numbers) {
        this.id = id;
        this.numbers = numbers;
    }

    public PlayNumbersDTO(PlayNumbers obj) {
        this.id = obj.getId();
        this.numbers = obj.getNumbers();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }
}
