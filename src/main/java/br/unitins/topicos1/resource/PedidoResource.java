package br.unitins.topicos1.resource;

import java.util.List;
import java.util.logging.Logger;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.Service.pedido.PedidoService;
import br.unitins.topicos1.dto.pedido.PedidoDTO;
import br.unitins.topicos1.dto.pedido.PedidoResponseDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pedidos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResource {

    private static final Logger LOG = Logger.getLogger(String.valueOf(PedidoResource.class));

    @Inject
    PedidoService pedidoService;

    @POST
    @RolesAllowed({"USER"})
    public Response createPedido(PedidoDTO pedidoDTO) {
        PedidoResponseDTO pedidoResponse = pedidoService.create(pedidoDTO);
        LOG.info("Pedido criado com sucesso");
        return Response.status(Response.Status.CREATED).entity(pedidoResponse).build();
    }

    @GET
    @Path("/usuario/{id}")
    @RolesAllowed({"USER", "ADM"})	
    public Response getPedidosByUsuario(@PathParam("id") Long usuarioId) {
        List<PedidoResponseDTO> pedidos = pedidoService.findByUserId(usuarioId);
        LOG.info("Busca todos os pedidos do usuario");
        return Response.ok(pedidos).build();
    }
}