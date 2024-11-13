package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Lente extends BaseEntity{
    private String tipoLente;
    private String tratamento;
    private String categoraiFiltroSolar;
    private Double astgmatismo;
    private Double miopia;
    private Double hipermetropia;
}
