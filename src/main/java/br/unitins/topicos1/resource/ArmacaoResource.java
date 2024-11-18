package br.unitins.topicos1.resource;

import br.unitins.topicos1.Service.ArmacaoService;
import br.unitins.topicos1.dto.ArmacaoDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/armacoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArmacaoResource {
    @Inject
    ArmacaoService armacaoService;

}
