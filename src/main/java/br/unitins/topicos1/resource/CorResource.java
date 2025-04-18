package br.unitins.topicos1.resource;

import java.util.logging.Logger;

import br.unitins.topicos1.Service.CorService;
import br.unitins.topicos1.dto.CorDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CorResource {
    private static final Logger LOG = Logger.getLogger(String.valueOf(CorResource.class));

    @Inject
    CorService corService;

    @POST
    @RolesAllowed({"ADM"})
    public Response create(CorDTO dto) {
        LOG.info("Cadastra uma cor");
        return Response.status(Response.Status.CREATED).entity(corService.create(dto)).build();
    }
    
    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADM"})
    public Response alter(@PathParam("id") Long id, CorDTO dto) {
        corService.alter(id, dto);
        LOG.info("Altera uma cor");
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADM"})
    public Response delete(@PathParam("id") Long id) {
        corService.delete(id);
        LOG.info("Deleta uma cor pelo id");
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    
    @GET
    @RolesAllowed({"ADM"})
    public Response findAll() {
        LOG.info("Busca todas as cores");
        return Response.ok(corService.findAll()).build();
    }
    
    @GET
    @Path("/{id}")
    @RolesAllowed({"ADM"})
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Busca uma cor pelo id");
        return Response.ok(corService.findById(id)).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({"ADM"})
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Busca uma cor pelo nome");
        return Response.ok(corService.FindByNome(nome)).build();
    }
}
