package br.unitins.topicos1.resource;

import java.util.logging.Logger;

import br.unitins.topicos1.Service.MarcaService;
import br.unitins.topicos1.dto.MarcaDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/marcas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MarcaResource {

    private static final Logger LOG = Logger.getLogger(String.valueOf(MarcaResource.class));

    @Inject
    MarcaService marcaService;

    @POST
    public Response create(MarcaDTO dto) {
        LOG.info("Cadastrar uma marca");
        return Response.status(Response.Status.CREATED).entity(marcaService.create(dto)).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response alter(@PathParam("id") Long id, MarcaDTO dto) {
        marcaService.alter(id, dto);
        LOG.info("Alterar uma marca");
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        marcaService.delete(id);
        LOG.info("Deletar uma marca pelo id");
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET 
    public Response findAll() {
        LOG.info("Buscar todas as marcas");
        return Response.ok(marcaService.findAll()).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    public Response findListNome(@QueryParam("nome") String nome) {
        LOG.info("Buscar varias marcas pelo nome");
        return Response.ok(marcaService.findByListNome(nome)).build();
    }
    
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        LOG.info("Buscar uma marca pelo id");
        return Response.ok(marcaService.findById(id)).build();
    }
    
    @GET
    @Path("/search/cnpj/{cnpj}")
    public Response findCnpj(@QueryParam("cnpj") String cnpj) {
        LOG.info("Buscar uma marca pelo cnpj");
        return Response.ok(marcaService.findByCnpj(cnpj)).build();
    }
}
