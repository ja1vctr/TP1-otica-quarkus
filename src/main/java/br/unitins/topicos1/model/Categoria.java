package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum Categoria {
    MASCULINO(1,"Masculino"),
    FEMININO(2,"Feminino"),
    UNISSEX(3,"Unissex"),
    INFANTIL(4,"Infantil");

    int id;
    String nome;

    Categoria(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public static Categoria valueOf(Integer id)throws IllegalArgumentException{
        for(Categoria categoria : Categoria.values()){
            if(id == categoria.id)
                return categoria;
        }
        throw new IllegalArgumentException("id Categoria invalido");
    }
}
