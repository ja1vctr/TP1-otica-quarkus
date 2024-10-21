package br.unitins.topicos1.resource;

import br.unitins.topicos1.Service.ArmacaoService;
import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.dto.MarcaDTO;
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
    @Path("/search/preco/{preco}")
    public Response findByPreco(@QueryParam("nome") Double preco,
                               @QueryParam("page") int page,
                               @QueryParam("pageSize") int pageSize) {
        return Response.ok(armacaoService.findByListPreco(preco, page, pageSize)).build();
    }
    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@QueryParam("nome") String nome,
                               @QueryParam("page") int page,
                               @QueryParam("pageSize") int pageSize) {
        return Response.ok(armacaoService.findByListNome(nome, page, pageSize)).build();
    }

    @GET
    @Path("/search/status/{status}")
    public Response findByStatus(@QueryParam("status") String status,
                               @QueryParam("page") int page,
                               @QueryParam("pageSize") int pageSize) {
        return Response.ok(armacaoService.findByListStatus(status, page, pageSize)).build();
    }

    @GET
    @Path("/search")
    public Response findAll(@QueryParam("page") int page,
                            @QueryParam("pageSize") int pageSize) {
        return Response.ok(armacaoService.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/search/filter")
    public Response findByPreco(@QueryParam("tamanho") String tamanho,
                                @QueryParam("formato") String formato,
                                @QueryParam("modelo") String modelo,
                                @QueryParam("preco") Double preco,
                                @QueryParam("cor") String cor,
                                @QueryParam("marca") String marca,
                                @QueryParam("page")int page,
                                @QueryParam("pageSize")int pageSize) {
        return Response.ok(armacaoService.dinamicSearch(tamanho, formato, modelo, preco, cor, marca, page, pageSize)).build();
    }
}
