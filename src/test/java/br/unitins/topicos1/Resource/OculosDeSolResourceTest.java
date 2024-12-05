package br.unitins.topicos1.Resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.Service.ProdutoService;
import br.unitins.topicos1.Service.hash.HashService;
import br.unitins.topicos1.Service.jwt.JwtService;
import br.unitins.topicos1.Service.user.UsuarioService;
import br.unitins.topicos1.dto.OculosDeSolDTO;
import br.unitins.topicos1.dto.OculosDeSolResponseDTO;
import br.unitins.topicos1.dto.user.AuthDTO;
import br.unitins.topicos1.dto.user.UsuarioResponseDTO;
import br.unitins.topicos1.model.OculosDeSol;
import br.unitins.topicos1.repository.OculosDeSolRepository;
import br.unitins.topicos1.resource.OculosDeSolResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestHTTPEndpoint(OculosDeSolResource.class)
public class OculosDeSolResourceTest {

    @Inject
    ProdutoService<OculosDeSolResponseDTO, OculosDeSolDTO> oculosDeSolService;

    @Inject 
    JwtService jwtService;

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    OculosDeSolRepository oculosDeSolRepository;

    @Test
    public void testFindAll() {
        AuthDTO authDTO = new AuthDTO("joao", "123456");
        String hashSenha = hashService.getHashSenha(authDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByUsernameAndSenha(authDTO.username(), hashSenha);
        String token = jwtService.generateJwt(result);
        given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .when().get()
            .then()
                .statusCode(200);
    }

    @Test
    public void testCreate() {
        AuthDTO authDTO = new AuthDTO("joao", "123456");
        String hashSenha = hashService.getHashSenha(authDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByUsernameAndSenha(authDTO.username(), hashSenha);
        String token = jwtService.generateJwt(result);

        OculosDeSolDTO dto = new OculosDeSolDTO(100.0, "oculos-1", 1, 100, "tamanho", "tipo", "material", 1L, "modelo", 1, "filtro", 1L);
        given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post()
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("oculos-1"),
                      "preco", is(100.0f));
    }

    @Test
    public void testUpdate() {
        AuthDTO authDTO = new AuthDTO("joao", "123456");
        String hashSenha = hashService.getHashSenha(authDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByUsernameAndSenha(authDTO.username(), hashSenha);
        String token = jwtService.generateJwt(result);

        // inserindo dado para alteracao (evitando a manipulacao de dados)
        OculosDeSolDTO novoDto = new OculosDeSolDTO(100.0, "oculos-2", 1, 100, "tamanho", "tipo", "material", 1L, "modelo", 1, "filtro", 1L);
        Long id = oculosDeSolService.create(novoDto).id();
        given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/" + id)
            .then()
                .statusCode(204);
        OculosDeSolResponseDTO oculosDeSol = oculosDeSolService.findById(id);
        assertEquals(oculosDeSol.nome(), "oculos-2");
        assertEquals(oculosDeSol.preco(), 100.0);
    }


    @Test
    public void testDelete() {
        AuthDTO authDTO = new AuthDTO("joao", "123456");
        String hashSenha = hashService.getHashSenha(authDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByUsernameAndSenha(authDTO.username(), hashSenha);
        String token = jwtService.generateJwt(result);

        // inserindo dado para alteracao (evitando a manipulacao de dados)
        OculosDeSolDTO dto = new OculosDeSolDTO(100.0, "oculos-3", 1, 100, "tamanho", "tipo", "material", 1L, "modelo", 1, "filtro", 1L);
        long id = oculosDeSolService.create(dto).id();
        given()
            .header("Authorization", "Bearer " + token)
            .when()
                .delete("/" + id)
            .then()
                .statusCode(204);
        // verificando se foi apagado no banco de dados
        OculosDeSol oculosDeSol = oculosDeSolRepository.findById(id);
        assertNull(oculosDeSol);
    }
}