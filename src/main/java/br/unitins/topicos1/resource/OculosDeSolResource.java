package br.unitins.topicos1.resource;

import java.io.IOException;
import java.util.logging.Logger;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.Service.OculosDeSolServiceImp;
import br.unitins.topicos1.Service.file.OculosDeSolFileServiceImp;
import br.unitins.topicos1.dto.OculosDeSolDTO;
import br.unitins.topicos1.form.LenteImageForm;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
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

@Path("/Oculos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OculosDeSolResource {

    private static final Logger LOG = Logger.getLogger(String.valueOf(OculosDeSolResource.class));
    
    @Inject
    OculosDeSolServiceImp oculosDeSolService;

    @Inject
    OculosDeSolFileServiceImp oculosDeSolFileService;

    @POST
    @RolesAllowed({"ADM"})
    public Response create(OculosDeSolDTO dto){
        LOG.info("Cadastra um oculos");
        return Response.status(Response.Status.CREATED).entity(oculosDeSolService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADM"})
    public Response alter(@PathParam("id") Long id, OculosDeSolDTO dto) {
        oculosDeSolService.alter(id, dto);
        LOG.info("Altera um oculos");
        return Response.status(Response.Status.NO_CONTENT).build();
    }  
    
    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADM"})
    public Response delete(@PathParam("id") Long id) {
        oculosDeSolService.delete(id);
        LOG.info("Deleta oculos");
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    public Response findAll() {
        LOG.info("Busca todos os oculos");
        return Response.ok(oculosDeSolService.findAll()).build();
    }
    
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Busca um oculos pelo id");
        return Response.ok(oculosDeSolService.findById(id)).build();
    }
    
    @GET
    @Path("/search/preco/{preco}")
    public Response findBypreco(@PathParam("preco") Double preco) {
        LOG.info("Busca varios oculos pelo preco");
        return Response.ok(oculosDeSolService.findByPreco(preco)).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    public Response findBynome(@PathParam("nome") String nome) {
        LOG.info("Busca varios oculos pelo nome");
        return Response.ok(oculosDeSolService.findByNome(nome)).build();
    }
    
    @GET
    @Path("/search/status/{status}")
    public Response findBystatus(@PathParam("status") Integer status) {
        LOG.info("Busca varios oculos pelo status");
        return Response.ok(oculosDeSolService.findByStatus(status)).build();
    }
    
    @GET
    @Path("/search/quantidade/{quantidade}")
    public Response findByquantidade(@PathParam("quantidade") Integer quantidade) {
        LOG.info("Busca varios oculos pela quantidade");
        return Response.ok(oculosDeSolService.findByQuantidade(quantidade)).build();
    }
    
    @GET
    @Path("/search/tamanho/{tamanho}")
    public Response findBytamanho(@PathParam("tamanho") String tamanho) {
        LOG.info("Busca varios oculos pelo tamanho");
        return Response.ok(oculosDeSolService.findByTamanho(tamanho)).build();
    }
    
    @GET
    @Path("/search/tipo/{tipo}")
    public Response findBytipo(@PathParam("tipo") String tipo) {
        LOG.info("Busca varios oculos pelo tipo");
        return Response.ok(oculosDeSolService.findByTipo(tipo)).build();
    }
    
    @GET
    @Path("/search/material/{material}")
    public Response findBymaterial(@PathParam("material") String material) {
        LOG.info("Busca varios oculos pelo material");
        return Response.ok(oculosDeSolService.findByMaterial(material)).build();
    }
    
    @GET
    @Path("/search/marca/{marca}")
    public Response findBystatus(@PathParam("marca") Long marca) {
        LOG.info("Busca varios oculos pela marca");
        return Response.ok(oculosDeSolService.findByMarca(marca)).build();
    }
    
    @PATCH
    @Path("/{id}/upload/imagem")
    @RolesAllowed({"ADM"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("id") Long id, @MultipartForm LenteImageForm form) {
        try {
            String nomeImagem = oculosDeSolFileService.save(form.getNomeImagem(), form.getImagem());
            
            oculosDeSolService.updateNomeImagem(id, nomeImagem);
            
        } catch (IOException e) {
            LOG.info(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity("Erro ao fazer upload da imagem. Verifique o log.")
            .build();
        }
        LOG.info("upload de imagem lente");
        return Response.noContent().build();
    }
    
    @GET
    @Path("/download/imagem/{nomeImagem}")
    @RolesAllowed({"ADM"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = 
            Response.ok(oculosDeSolFileService.find(nomeImagem));
            response.header("Content-Disposition", "attachment; filename="+nomeImagem);
            LOG.info("download de imagem lente: " + nomeImagem);
            return response.build();
    }
}
