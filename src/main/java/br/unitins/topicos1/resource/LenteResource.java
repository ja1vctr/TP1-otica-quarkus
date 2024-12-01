package br.unitins.topicos1.resource;

import java.io.IOException;
import java.util.logging.Logger;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.Service.LenteServiceImp;
import br.unitins.topicos1.Service.file.LenteFileServiceImp;
import br.unitins.topicos1.dto.LenteDTO;
import br.unitins.topicos1.form.LenteImageForm;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/lentes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LenteResource {
    
    private static final Logger LOG = Logger.getLogger(String.valueOf(LenteResource.class));

    @Inject
    LenteServiceImp lenteService;
    
    @Inject
    LenteFileServiceImp lenteFileService;

    @POST
    public Response create(@Valid LenteDTO dto){
        LOG.info("Cadastra uma lente");
        return Response.status(Response.Status.CREATED).entity(lenteService.create(dto)).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response alter(@Valid @PathParam("id") Long id, LenteDTO dto) {
        lenteService.alter(id, dto);
        LOG.info("Altera uma lente");
        return Response.status(Response.Status.NO_CONTENT).build();
    }  
    
    @DELETE
    @Path("/{id}")
    public Response delete(@Valid @PathParam("id") Long id) {
        lenteService.delete(id);
        LOG.info("Deleta uma lente pelo id");
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    public Response findAll() {
        LOG.info("Busca todas as lentes");
        return Response.ok(lenteService.findAll()).build();
    }
    
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Busca uma lente pelo id");
        return Response.ok(lenteService.findById(id)).build();
    }
    
    @GET
    @Path("/search/preco/{preco}")
    public Response findBypreco(@PathParam("preco") Double preco) {
        LOG.info("Busca varias lentes pelo preco");
        return Response.ok(lenteService.findByPreco(preco)).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    public Response findBynome(@PathParam("nome") String nome) {
        LOG.info("Busca varias lentes pelo nome");
        return Response.ok(lenteService.findByNome(nome)).build();
    }
    
    @GET
    @Path("/search/status/{status}")
    public Response findBystatus(@PathParam("status") Integer status) {
            LOG.info("Busca varias lentes pelo status");
            return Response.ok(lenteService.findByStatus(status)).build();
        }
        
    @GET
    @Path("/search/quantidade/{quantidade}")
    public Response findByquantidade(@PathParam("quantidade") Integer quantidade) {
    LOG.info("Busca varias lentes pela quantidade");
    return Response.ok(lenteService.findByQuantidade(quantidade)).build();
    }
    
    @GET
    @Path("/search/tamanho/{tamanho}")
    public Response findBytamanho(@PathParam("tamanho") String tamanho) {
        LOG.info("Busca varias lentes pelo tamanho");
        return Response.ok(lenteService.findByTamanho(tamanho)).build();
    }
    
    @GET
    @Path("/search/tipo/{tipo}")
    public Response findBytipo(@PathParam("tipo") String tipo) {
        LOG.info("Busca varias lentes pelo tipo");
        return Response.ok(lenteService.findByTipo(tipo)).build();
    }
    
    @GET
    @Path("/search/material/{material}")
    public Response findBymaterial(@PathParam("material") String material) {
        LOG.info("Busca varias lentes pelo material");
        return Response.ok(lenteService.findByMaterial(material)).build();
    }
    
    @GET
    @Path("/search/marca/{marca}")
    public Response findBystatus(@PathParam("marca") Long marca) {
        LOG.info("Busca varias lentes pela marca");
        return Response.ok(lenteService.findByMarca(marca)).build();
    }

     @PATCH
    @Path("/{id}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("id") Long id, @MultipartForm LenteImageForm form) {
        try {
            String nomeImagem = lenteFileService.save(form.getNomeImagem(), form.getImagem());
            lenteService.updateNomeImagem(id, nomeImagem);
        } catch (IOException e) {
           Response.status(500).build();
        }
        return Response.noContent().build();
    }
    @GET
    @Path("/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = 
            Response.ok(lenteFileService.find(nomeImagem));
            response.header("Content-Disposition", "attachment; filename="+nomeImagem);
            return response.build();
    }
}
