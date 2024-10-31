package br.unitins.topicos1.resource;

import br.unitins.topicos1.Service.AcessorioService;
import br.unitins.topicos1.dto.AcessorioDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("acessorios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AcessorioResource {
    @Inject
    AcessorioService acessorioService;

    @POST
    public Response create(AcessorioDTO dto) {

        return Response.status(Response.Status.CREATED).entity(acessorioService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alter(@PathParam("id") Long id, AcessorioDTO dto) {
        acessorioService.alter(id, dto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        acessorioService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @GET
    public Response findAll() {
        return Response.ok(acessorioService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(acessorioService.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findBynome(@PathParam("nome") String nome) {
        return Response.ok(acessorioService.findByNome(nome)).build();
    }

    @GET
    @Path("/search/status/{status}")
    public Response findByStatus(@PathParam("status") Long status) {
        return Response.ok(acessorioService.findByStatus(status)).build();
    }
    @GET
    @Path("/search/descricao/{descricao}")
    public Response findByDescricao(@PathParam("descricao") String descricao) {
        return Response.ok(acessorioService.findByDescricao(descricao)).build();
    }


}
