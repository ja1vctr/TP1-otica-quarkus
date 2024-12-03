package br.unitins.topicos1.resource;

import br.unitins.topicos1.Service.user.UsuarioServiceImp;
import br.unitins.topicos1.dto.cadastro.AlterCadastroDTO;
import br.unitins.topicos1.dto.cadastro.CadastroDTO;
import jakarta.annotation.security.RolesAllowed;
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

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    
    @Inject
    UsuarioServiceImp usuarioService;
    
    @POST
    @RolesAllowed({"ADM"})
    public Response create(@Valid CadastroDTO dto){
        return Response.status(Response.Status.CREATED).entity(usuarioService.create(dto)).build();
    }
    
    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADM"})
    public Response alter(@Valid @PathParam("id") Long id, AlterCadastroDTO dto) {
        usuarioService.alter(id, dto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }  
    
    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADM"})
    public Response delete(@Valid @PathParam("id") Long id) {
        usuarioService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Path("/{id}")
    @RolesAllowed({"ADM"})
    public Response findById(@Valid @PathParam("id") Long id) {
        return Response.status(Response.Status.OK).entity(usuarioService.findById(id)).build();
    }
    
    @GET
    @RolesAllowed({"ADM"})
    public Response findAll() {
        return Response.status(Response.Status.OK).entity(usuarioService.findAll()).build();
    }
    
    @GET
    @RolesAllowed({"ADM"})
    public Response findByNome() {
        return Response.status(Response.Status.OK).entity(usuarioService.findAll()).build();
    }
}
