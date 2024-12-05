package br.unitins.topicos1.resource;

import java.io.IOException;
import java.util.logging.Logger;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.Service.ArmacaoServiceImp;
import br.unitins.topicos1.Service.file.ArmacaoFileServiceImp;
import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.form.LenteImageForm;
import jakarta.annotation.security.RolesAllowed;
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

@Path("/armacoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArmacaoResource {
    private static final Logger LOG = Logger.getLogger(String.valueOf(ArmacaoResource.class));

    @Inject
    ArmacaoServiceImp armacaoService;
    
    @Inject
    ArmacaoFileServiceImp armacaoFileService;

    @POST
    @RolesAllowed({"ADM"})
    public Response create(@Valid ArmacaoDTO dto){
        LOG.info("Cadastra uma armacao");
        return Response.status(Response.Status.CREATED).entity(armacaoService.create(dto)).build();
    }
    
    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADM"})
    public Response alter(@Valid @PathParam("id") Long id, ArmacaoDTO dto) {
        armacaoService.alter(id, dto);
        LOG.info("Altera uma armacao");
        return Response.status(Response.Status.NO_CONTENT).build();
    }  
    
    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADM"})
    public Response delete(@Valid @PathParam("id") Long id) {
        armacaoService.delete(id);
        LOG.info("Deleta uma armacao pelo id");
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    public Response findAll() {
        LOG.info("Busca todas as armacao");
        return Response.ok(armacaoService.findAll()).build();
    }
    
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Busca uma armacao pelo id");
        return Response.ok(armacaoService.findById(id)).build();
    }
    
    @GET
    @Path("/search/preco/{preco}")
    public Response findBypreco(@PathParam("preco") Double preco) {
        LOG.info("Busca varias armacao pelo preco");
        return Response.ok(armacaoService.findByPreco(preco)).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    public Response findBynome(@PathParam("nome") String nome) {
        LOG.info("Busca varias armacao pelo nome");
        return Response.ok(armacaoService.findByNome(nome)).build();
    }
    
    @GET
    @Path("/search/status/{status}")
    public Response findBystatus(@PathParam("status") Integer status) {
        LOG.info("Busca varias armacao pelo status");
        return Response.ok(armacaoService.findByStatus(status)).build();
    }
    
    @GET
    @RolesAllowed({"ADM"})
    @Path("/search/quantidade/{quantidade}")
    public Response findByquantidade(@PathParam("quantidade") Integer quantidade) {
    LOG.info("Busca varias armacao pela quantidade");
    return Response.ok(armacaoService.findByQuantidade(quantidade)).build();
    }
    
    @GET
    @Path("/search/tamanho/{tamanho}")
    public Response findBytamanho(@PathParam("tamanho") String tamanho) {
        LOG.info("Busca varias armacao pelo tamanho");
        return Response.ok(armacaoService.findByTamanho(tamanho)).build();
    }
    
    @GET
    @Path("/search/tipo/{tipo}")
    public Response findBytipo(@PathParam("tipo") String tipo) {
        LOG.info("Busca varias armacao pelo tipo");
        return Response.ok(armacaoService.findByTipo(tipo)).build();
    }
    
    @GET
    @Path("/search/material/{material}")
    public Response findBymaterial(@PathParam("material") String material) {
        LOG.info("Busca varias armacao pelo material");
        return Response.ok(armacaoService.findByMaterial(material)).build();
    }
    
    @GET
    @Path("/search/marca/{marca}")
    public Response findBystatus(@PathParam("marca") Long marca) {
        LOG.info("Busca varias armacao pela marca");
            return Response.ok(armacaoService.findByMarca(marca)).build();
    }

    @PATCH
    @Path("/{id}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed({"ADM"})
    public Response uploadImage(@PathParam("id") Long id, @MultipartForm LenteImageForm form) {
        try {
            String nomeImagem = armacaoFileService.save(form.getNomeImagem(), form.getImagem());
            
            armacaoService.updateNomeImagem(id, nomeImagem);
        
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
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @RolesAllowed({"ADM"})
    public Response downloadImage(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = 
            Response.ok(armacaoFileService.find(nomeImagem));
            response.header("Content-Disposition", "attachment; filename="+nomeImagem);
            LOG.info("download de imagem lente: " + nomeImagem);
            return response.build();
    }
}
