package me.henrique.lotteryzup.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {

    private Long id;

    @Email(message = "Informe um e-mail válido")
    @NotEmpty(message = "E-mail é obrigatório")
    private String email;
}
