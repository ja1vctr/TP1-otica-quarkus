package br.unitins.topicos1.resource;

import java.util.logging.Logger;

import br.unitins.topicos1.Service.pedido.PagamentoService;
import br.unitins.topicos1.dto.pagamento.CartaoCreditoDTO;
import br.unitins.topicos1.dto.pagamento.PagamentoResponseDTO;
import br.unitins.topicos1.dto.pagamento.PixDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pagamentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PagamentoResource {

    @Inject
     PagamentoService pagamentoService;

    private static final Logger LOG = Logger.getLogger(String.valueOf(PagamentoResource.class));

     @POST
     @Path("/pix")
     @RolesAllowed({"USER"})
     public Response pagarPix(PixDTO dto) {
         PagamentoResponseDTO pagamentoResponse = pagamentoService.pagarPix(dto);
         LOG.info("Pagamento realizado com sucesso");   
         return Response.status(Response.Status.CREATED).entity(pagamentoResponse).build();
     }

    @POST
    @Path("/credito")
    @RolesAllowed({"USER"})
    public Response pagarCredito(CartaoCreditoDTO dto) {
        PagamentoResponseDTO pagamentoResponse = pagamentoService.pagarCredito(dto);
        LOG.info("Pagamento realizado com sucesso");
        return Response.status(Response.Status.CREATED).entity(pagamentoResponse).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"USER"})
    public Response getPagamento(@PathParam("id") Long id) {
        PagamentoResponseDTO pagamentoResponse = pagamentoService.findById(id);
        LOG.info("Busca pagamento por id");
        return Response.ok(pagamentoResponse).build();
    }
}