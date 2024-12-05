package br.unitins.topicos1.Resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.resource.MarcaResource;
import br.unitins.topicos1.Service.MarcaService;
import br.unitins.topicos1.Service.MarcaServiceImp;
import br.unitins.topicos1.Service.ProdutoService;
import br.unitins.topicos1.Service.jwt.JwtService;
import br.unitins.topicos1.Service.hash.HashService;
import br.unitins.topicos1.Service.user.UsuarioService;
import br.unitins.topicos1.dto.user.AuthDTO;
import br.unitins.topicos1.dto.user.UsuarioResponseDTO;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestHTTPEndpoint(MarcaResource.class)
public class MarcaResourceTest {

    @Inject
    MarcaService marcaService;

    @Inject 
    JwtService jwtService;

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    MarcaRepository marcaRepository;

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

        MarcaDTO dto = new MarcaDTO("marca-teste", "12345678901234");
        given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post()
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("marca-teste"),
                      "cnpj", is("12345678901234"));
    }

    @Test
    public void testUpdate() {
        AuthDTO authDTO = new AuthDTO("joao", "123456");
        String hashSenha = hashService.getHashSenha(authDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByUsernameAndSenha(authDTO.username(), hashSenha);
        String token = jwtService.generateJwt(result);

        // inserindo dado para alteracao (evitando a manipulacao de dados)
        MarcaDTO novoDto = new MarcaDTO("teste2", "xxxxxxxxxxxxxx");
        Long id = marcaService.create(novoDto).id();
        given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/" + id)
            .then()
                .statusCode(204);
        MarcaResponseDTO marca = marcaService.findById(id);
        assertEquals(marca.nome(), "teste2");
        assertEquals(marca.cnpj(), "xxxxxxxxxxxxxx");
    }

    @Test
    public void testDelete() {
        AuthDTO authDTO = new AuthDTO("joao", "123456");
        String hashSenha = hashService.getHashSenha(authDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByUsernameAndSenha(authDTO.username(), hashSenha);
        String token = jwtService.generateJwt(result);

        // inserindo dado para alteracao (evitando a manipulacao de dados)
        MarcaDTO dto = new MarcaDTO("marca-teste3", "zzzzzzzzzzzzzz");
        long id = marcaService.create(dto).id();
        given()
            .header("Authorization", "Bearer " + token)
            .when()
                .delete("/" + id)
            .then()
                .statusCode(204);
        // verificando se foi apagado no banco de dados
        Marca marca = marcaRepository.findById(id);
        assertNull(marca);
    }
}