package br.unitins.topicos1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Armacao extends BaseEntity {
    private String nome;
    private String material;
    private String formato;
    private String modelo;
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "id_medida")
    private Medida medida;
    @ManyToOne
    @JoinColumn(name = "id_cor")
    private Cor cor;
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;
}
