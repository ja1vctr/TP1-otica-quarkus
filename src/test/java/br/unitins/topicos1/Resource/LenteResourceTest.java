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
import br.unitins.topicos1.dto.LenteDTO;
import br.unitins.topicos1.dto.LenteResponseDTO;
import br.unitins.topicos1.dto.user.AuthDTO;
import br.unitins.topicos1.dto.user.UsuarioResponseDTO;
import br.unitins.topicos1.model.Lente;
import br.unitins.topicos1.repository.LenteRepository;
import br.unitins.topicos1.resource.LenteResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestHTTPEndpoint(LenteResource.class)
public class LenteResourceTest {

    @Inject
    ProdutoService<LenteResponseDTO, LenteDTO> lenteService;

    @Inject 
    JwtService jwtService;

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    LenteRepository lenteRepository;

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

        LenteDTO dto = new LenteDTO(100.0, "lente-1", 1, 100, "tamanho", "tipo", "material", 1L, "tratamento", "espessura", "receita");
        given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post()
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("lente-1"),
                      "preco", is(100.0f));
    }

    @Test
    public void testUpdate() {
        AuthDTO authDTO = new AuthDTO("joao", "123456");
        String hashSenha = hashService.getHashSenha(authDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByUsernameAndSenha(authDTO.username(), hashSenha);
        String token = jwtService.generateJwt(result);

        // inserindo dado para alteracao (evitando a manipulacao de dados)
        LenteDTO novoDto = new LenteDTO(100.0, "lente-2", 1, 100, "tamanho", "tipo", "material", 1L, "tratamento", "espessura", "receita");
        Long id = lenteService.create(novoDto).id();
        given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/" + id)
            .then()
                .statusCode(204);
        LenteResponseDTO lente = lenteService.findById(id);
        assertEquals(lente.nome(), "lente-2");
        assertEquals(lente.preco(), 100.0);
    }

    @Test
    public void testDelete() {
        AuthDTO authDTO = new AuthDTO("joao", "123456");
        String hashSenha = hashService.getHashSenha(authDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByUsernameAndSenha(authDTO.username(), hashSenha);
        String token = jwtService.generateJwt(result);

        // inserindo dado para alteracao (evitando a manipulacao de dados)
        LenteDTO dto = new LenteDTO(100.0, "lente-3", 1, 100, "tamanho", "tipo", "material", 1L, "tratamento", "espessura", "receita");
        long id = lenteService.create(dto).id();
        given()
            .header("Authorization", "Bearer " + token)
            .when()
                .delete("/" + id)
            .then()
                .statusCode(204);
        // verificando se foi apagado no banco de dados
        Lente lente = lenteRepository.findById(id);
        assertNull(lente);
    }
}