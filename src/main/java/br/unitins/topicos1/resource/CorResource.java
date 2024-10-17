package br.unitins.topicos1.resource;

import br.unitins.topicos1.Service.CorService;
import br.unitins.topicos1.dto.CorDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CorResource {
    @Inject
    CorService corService;

    @POST
    public Response create(CorDTO dto) {

        return Response.status(Response.Status.CREATED).entity(corService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alter(@PathParam("id") Long id, CorDTO dto) {
        corService.alter(id, dto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        corService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @GET
    public Response findAll() {
        return Response.ok(corService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(corService.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(corService.FindByNome(nome)).build();
    }
}
