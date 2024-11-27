package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Lente extends Produto{
    private String tratamento;
    private String espessura;
    private String receita;
}
