package br.unitins.topicos1.model.user;

import br.unitins.topicos1.model.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario extends BaseEntity{
    private String nome;
    private String userName;
    private String senha;
    private Perfil perfil;
}
