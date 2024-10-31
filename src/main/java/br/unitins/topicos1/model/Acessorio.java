package br.unitins.topicos1.model;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Acessorio extends Produto{
    private String descricao;
}
