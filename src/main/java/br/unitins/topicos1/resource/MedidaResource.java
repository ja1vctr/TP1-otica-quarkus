package br.unitins.topicos1.resource;

import br.unitins.topicos1.Service.MedidaService;
import br.unitins.topicos1.dto.MedidaDTO;
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

@Path("/medidas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedidaResource {
    @Inject
    MedidaService medidaService;

    @POST
    public Response create(MedidaDTO dto) {

        return Response.status(Response.Status.CREATED).entity(medidaService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alter(@PathParam("id") Long id, MedidaDTO dto) {
        medidaService.alter(id, dto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        medidaService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(medidaService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(medidaService.findById(id)).build();
    }

    @GET
    @Path("/search/descricao/{descricao}")
    public Response findByNome(@PathParam("descricao") String descricao) {
        return Response.ok(medidaService.findByDescricao(descricao)).build();
    }
}
