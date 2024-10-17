package br.unitins.topicos1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Armacao extends Produto {
    private String material;
    private String tamanho;
    private String formato;
    private String curvaLente;
    private String modelo;
    @ManyToOne
    @JoinColumn(name = "id_cor")
    private Cor cor;
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;
}
