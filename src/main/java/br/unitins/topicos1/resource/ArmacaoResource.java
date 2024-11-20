package br.unitins.topicos1.resource;

import java.io.ObjectInputFilter.Status;

import br.unitins.topicos1.Service.ArmacaoService;
import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.dto.CorDTO;
import br.unitins.topicos1.validation.ValidationExceptionMapper;
import jakarta.inject.Inject;
import jakarta.validation.ValidationException;
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
    public Response findAll() {
        return Response.ok(armacaoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(armacaoService.findById(id)).build();
    }

    @GET
    @Path("/search/preco/{preco}")
    public Response findBypreco(@PathParam("preco") Double preco) {
        return Response.ok(armacaoService.findByPreco(preco)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findBynome(@PathParam("nome") String nome) {
        return Response.ok(armacaoService.findByNome(nome)).build();
    }

    @GET
    @Path("/search/status/{status}")
    public Response findBystatus(@PathParam("status") Integer status) {
        try{
            return Response.ok(armacaoService.findByStatus(status)).build();
        }catch(Exception e){
            throw new br.unitins.topicos1.validation.ValidationException(e.getLocalizedMessage(), e.getMessage());
        }
        
    }

    @GET
    @Path("/search/quantidade/{quantidade}")
    public Response findByquantidade(@PathParam("quantidade") Integer quantidade) {
        return Response.ok(armacaoService.findByQuantidade(quantidade)).build();
    }

    @GET
    @Path("/search/tamanho/{tamanho}")
    public Response findBytamanho(@PathParam("tamanho") String tamanho) {
        return Response.ok(armacaoService.findByTamanho(tamanho)).build();
    }

    @GET
    @Path("/search/tipo/{tipo}")
    public Response findBytipo(@PathParam("tipo") String tipo) {
        return Response.ok(armacaoService.findByTipo(tipo)).build();
    }

    @GET
    @Path("/search/material/{material}")
    public Response findBymaterial(@PathParam("material") String material) {
        return Response.ok(armacaoService.findByMaterial(material)).build();
    }

    @GET
    @Path("/search/marca/{marca}")
    public Response findBystatus(@PathParam("marca") Long marca) {
        try{
            return Response.ok(armacaoService.findByMarca(marca)).build();
        }catch(Exception e){
            throw new br.unitins.topicos1.validation.ValidationException(e.getLocalizedMessage(), e.getMessage());
            }
        
    }
}
