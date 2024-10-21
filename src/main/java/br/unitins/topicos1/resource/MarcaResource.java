package br.unitins.topicos1.resource;

import br.unitins.topicos1.Service.MarcaService;
import br.unitins.topicos1.dto.MarcaDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/marcas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MarcaResource {
    @Inject
    MarcaService marcaService;

    @POST
    public Response create(MarcaDTO dto) {

        return Response.status(Response.Status.CREATED).entity(marcaService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alter(@PathParam("id") Long id, MarcaDTO dto) {
        marcaService.alter(id, dto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        marcaService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/buscar")
    public Response findAll(@QueryParam("page") int page,
                            @QueryParam("pageSize") int pageSize) {
        return Response.ok(marcaService.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findListNome(@QueryParam("nome") String nome,
                            @QueryParam("page") int page,
                            @QueryParam("pageSize") int pageSize) {
        return Response.ok(marcaService.findByListNome(nome, page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(marcaService.findById(id)).build();
    }
}
