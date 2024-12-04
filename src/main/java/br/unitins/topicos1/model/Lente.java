package br.unitins.topicos1.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("LENTE")
public class Lente extends Produto{
    private String tratamento;
    private String espessura;
    private String receita;
}
