package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Armacao extends Produto {
    private String formato;
    private Categoria categoria;
    private String curvaDaLente;
    @ManyToOne
    @JoinColumn(name = "id_cor")
    private Cor cor;
}
