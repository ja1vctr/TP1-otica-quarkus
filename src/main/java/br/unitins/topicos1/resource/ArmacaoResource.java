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

    @POST
    public Response create(ArmacaoDTO dto){
        return Response.status(Response.Status.CREATED).entity(armacaoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alter(@PathParam("id") Long id, ArmacaoDTO dto) {
        armacaoService.alter(id, dto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        armacaoService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(armacaoService.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@QueryParam("nome") String nome,
                               @QueryParam("page") int page,
                               @QueryParam("pageSize") int pageSize) {
        return Response.ok(armacaoService.findByListNome(nome, page, pageSize)).build();
    }

    @GET
    @Path("/search/medida/{medida}")
    public Response findByMedida(@QueryParam("medida") Integer medida,
                                 @QueryParam("page") int page,
                                 @QueryParam("pageSize") int pageSize) {
        return Response.ok(armacaoService.findByListMedida(medida, page, pageSize)).build();
    }

    @GET
    @Path("/search/formato/{formato}")
    public Response findByFormato(@QueryParam("formato") String formato,
                                 @QueryParam("page") int page,
                                 @QueryParam("pageSize") int pageSize) {
        return Response.ok(armacaoService.findByListFormato(formato, page, pageSize)).build();
    }

    @GET
    @Path("/search/modelo/{modelo}")
    public Response findByModelo(@QueryParam("modelo") String modelo,
                                 @QueryParam("page") int page,
                                 @QueryParam("pageSize") int pageSize) {
        return Response.ok(armacaoService.findByListModelo(modelo, page, pageSize)).build();
    }

    @GET
    @Path("")
    public Response findAll(@QueryParam("page") int page,
                            @QueryParam("pageSize") int pageSize) {
        return Response.ok(armacaoService.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/search/filter")
    public Response findByPreco(@QueryParam("tamanho") String medida,
                                @QueryParam("formato") String formato,
                                @QueryParam("modelo") String modelo,
                                @QueryParam("preco") Double preco,
                                @QueryParam("cor") String cor,
                                @QueryParam("marca") String marca,
                                @QueryParam("page")int page,
                                @QueryParam("pageSize")int pageSize) {
        return Response.ok(armacaoService.dinamicSearch(medida, formato, modelo, preco, cor, marca, page, pageSize)).build();
    }
}
