package me.henrique.lotteryzup.api.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PersonDTO {

    private Long id;

    @Email(message = "Informe um e-mail válido")
    @NotEmpty(message = "E-mail é obrigatório")
    private String email;

    public PersonDTO() {
    }

    public PersonDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
