package br.unitins.topicos1.Resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.Service.CorService;
import br.unitins.topicos1.Service.hash.HashService;
import br.unitins.topicos1.Service.jwt.JwtService;
import br.unitins.topicos1.Service.user.UsuarioService;
import br.unitins.topicos1.dto.CorDTO;
import br.unitins.topicos1.dto.CorResponseDTO;
import br.unitins.topicos1.dto.user.AuthDTO;
import br.unitins.topicos1.dto.user.UsuarioResponseDTO;
import br.unitins.topicos1.model.Cor;
import br.unitins.topicos1.repository.CorRepository;
import br.unitins.topicos1.resource.CorResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestHTTPEndpoint(CorResource.class)
public class CorResourceTest {

    @Inject
    CorService corService;

    @Inject 
    JwtService jwtService;

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    CorRepository corRepository;

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

        CorDTO dto = new CorDTO("cor-teste");
        given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post()
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("cor-teste"));
    }

    @Test
    public void testUpdate() {
        AuthDTO authDTO = new AuthDTO("joao", "123456");
        String hashSenha = hashService.getHashSenha(authDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByUsernameAndSenha(authDTO.username(), hashSenha);
        String token = jwtService.generateJwt(result);

        // inserindo dado para alteracao (evitando a manipulacao de dados)
        CorDTO novoDto = new CorDTO("cor-teste2");
        Long id = corService.create(novoDto).id();
        given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/" + id)
            .then()
                .statusCode(204);
        CorResponseDTO cor = corService.findById(id);
        assertEquals(cor.nome(), "cor-teste2");
    }

    @Test
    public void testDelete() {
        AuthDTO authDTO = new AuthDTO("joao", "123456");
        String hashSenha = hashService.getHashSenha(authDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByUsernameAndSenha(authDTO.username(), hashSenha);
        String token = jwtService.generateJwt(result);

        // inserindo dado para alteracao (evitando a manipulacao de dados)
        CorDTO dto = new CorDTO("cor-teste3");
        long id = corService.create(dto).id();
        given()
            .header("Authorization", "Bearer " + token)
            .when()
                .delete("/" + id)
            .then()
                .statusCode(204);
        // verificando se foi apagado no banco de dados
        Cor cor = corRepository.findById(id);
        assertNull(cor);
    }
}