package br.unitins.topicos1.model;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_produto")
public abstract class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    @SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @PositiveOrZero(message = "Este campo nao pode ser negativo")
    private Double preco;

    @NotBlank(message = "Este campo não pode ser null")
    private String nome;

    @NotNull
    @Column(name = "id_status")
    private Status status;

    @NotNull
    private Integer quantidade;

    private String tamanho;
    private String tipo;
    private String material;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    private String nomeImagem;
}