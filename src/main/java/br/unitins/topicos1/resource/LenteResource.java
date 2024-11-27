package br.unitins.topicos1.resource;

import br.unitins.topicos1.Service.LenteServiceImp;
import br.unitins.topicos1.dto.LenteDTO;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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

@Path("/lentes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LenteResource {
    
    @Inject
    LenteServiceImp lenteService;

    @POST
    public Response create(@Valid LenteDTO dto){
        return Response.status(Response.Status.CREATED).entity(lenteService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alter(@Valid @PathParam("id") Long id, LenteDTO dto) {
        lenteService.alter(id, dto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }  

    @DELETE
    @Path("/{id}")
    public Response delete(@Valid @PathParam("id") Long id) {
        lenteService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(lenteService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(lenteService.findById(id)).build();
    }

    @GET
    @Path("/search/preco/{preco}")
    public Response findBypreco(@PathParam("preco") Double preco) {
        return Response.ok(lenteService.findByPreco(preco)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findBynome(@PathParam("nome") String nome) {
        return Response.ok(lenteService.findByNome(nome)).build();
    }

    @GET
    @Path("/search/status/{status}")
    public Response findBystatus(@PathParam("status") Integer status) {
        try{
            return Response.ok(lenteService.findByStatus(status)).build();
        }catch(Exception e){
            throw new ValidationException("status", e.getMessage());
        }
        
    }

    @GET
    @Path("/search/quantidade/{quantidade}")
    public Response findByquantidade(@PathParam("quantidade") Integer quantidade) {
        return Response.ok(lenteService.findByQuantidade(quantidade)).build();
    }

    @GET
    @Path("/search/tamanho/{tamanho}")
    public Response findBytamanho(@PathParam("tamanho") String tamanho) {
        return Response.ok(lenteService.findByTamanho(tamanho)).build();
    }

    @GET
    @Path("/search/tipo/{tipo}")
    public Response findBytipo(@PathParam("tipo") String tipo) {
        return Response.ok(lenteService.findByTipo(tipo)).build();
    }

    @GET
    @Path("/search/material/{material}")
    public Response findBymaterial(@PathParam("material") String material) {
        return Response.ok(lenteService.findByMaterial(material)).build();
    }

    @GET
    @Path("/search/marca/{marca}")
    public Response findBystatus(@PathParam("marca") Long marca) {
            return Response.ok(lenteService.findByMarca(marca)).build();
    }
}
