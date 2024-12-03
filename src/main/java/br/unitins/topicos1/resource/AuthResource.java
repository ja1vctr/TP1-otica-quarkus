package br.unitins.topicos1.resource;


import br.unitins.topicos1.Service.hash.HashService;
import br.unitins.topicos1.Service.jwt.JwtService;
import br.unitins.topicos1.Service.user.UsuarioService;
import br.unitins.topicos1.dto.user.AuthDTO;
import br.unitins.topicos1.dto.user.UsuarioResponseDTO;
import br.unitins.topicos1.model.user.Usuario;
import org.jboss.logging.Logger;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    
    @Inject
    HashService hashService;
    
    @Inject
    UsuarioService usuarioService;
    
    @Inject
    JwtService jwtService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@Valid AuthDTO authDTO) {
        LOG.infof("Iniciando a autenticacao do %s", authDTO.username());

        String hash = hashService.getHashSenha(authDTO.senha());
        UsuarioResponseDTO usuario = usuarioService.findByUsernameAndSenha(authDTO.username(), hash);

        if (usuario == null) {
            return Response.status(Status.NO_CONTENT)
                .entity("Usuario não encontrado").build();
        } 
        LOG.info("Efetuando login");
        return Response.ok()
            .header("Authorization", jwtService.generateJwt(usuario))
            .build();   
    }
}