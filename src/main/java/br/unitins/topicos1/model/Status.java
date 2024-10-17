package br.unitins.topicos1.model;

import lombok.Getter;

@Getter
public enum Status {
    DISPONIVEL(1,"Disponivel"),
    INDISPONIVEL(2,"Indisponivel");

    int id;
    String nome;

    Status(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public static Status valueOf(Integer id)throws IllegalArgumentException{
        for(Status status : Status.values()){
            if(id == status.id)
                return status;
        }
        throw new IllegalArgumentException("id Status invalido");
    }
}
