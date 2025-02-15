
package br.unitins.topicos1.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("OCULOS_DE_SOL")
public class OculosDeSol extends Produto {
    private String modelo;
    private Categoria categoria;
    private String filtroSolar;
    @ManyToOne
    @JoinColumn(name = "id_cor")
    private Cor cor;
}
