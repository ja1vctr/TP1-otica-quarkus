package br.unitins.topicos1.form;

import lombok.Getter;
import lombok.Setter;
import org.jboss.resteasy.annotations.providers.multipart.PartType;
import jakarta.ws.rs.FormParam;

@Getter
@Setter
public class LenteImageForm {
    @FormParam("nomeImagem")
    private String nomeImagem;
    @FormParam("imagem")
    @PartType("application/octet-stream")
    private byte[] imagem;
    public String getNomeImagem() {
        return nomeImagem;
    }

}
