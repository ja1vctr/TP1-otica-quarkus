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
import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.dto.ArmacaoResponseDTO;
import br.unitins.topicos1.dto.user.AuthDTO;
import br.unitins.topicos1.dto.user.UsuarioResponseDTO;
import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.repository.ArmacaoRepository;
import br.unitins.topicos1.resource.ArmacaoResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestHTTPEndpoint(ArmacaoResource.class)
public class ArmacaoResourceTest {

    @Inject
    ProdutoService<ArmacaoResponseDTO, ArmacaoDTO> armacaoService;

    @Inject 
    JwtService jwtService;

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    ArmacaoRepository armacaoRepository;

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

        ArmacaoDTO dto = new ArmacaoDTO(100.0, "armacao-1", 1, 100, "tamanho", "tipo", "material", 1L, "formato", 1, "curvaDaLente", 1L);
        given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post()
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("armacao-1"),
                      "preco", is(100.0f));
    }

    @Test
    public void testUpdate() {
        AuthDTO authDTO = new AuthDTO("joao", "123456");
        String hashSenha = hashService.getHashSenha(authDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByUsernameAndSenha(authDTO.username(), hashSenha);
        String token = jwtService.generateJwt(result);

        // inserindo dado para alteracao (evitando a manipulacao de dados)
        ArmacaoDTO novoDto = new ArmacaoDTO(100.0, "armacao-2", 1, 100, "tamanho", "tipo", "material", 1L, "formato", 1, "curvaDaLente", 1L);
        Long id = armacaoService.create(novoDto).id();
        given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/" + id)
            .then()
                .statusCode(204);
        ArmacaoResponseDTO armacao = armacaoService.findById(id);
        assertEquals(armacao.nome(), "armacao-2");
        assertEquals(armacao.preco(), 100.0);
    }

    @Test
    public void testDelete() {
        AuthDTO authDTO = new AuthDTO("joao", "123456");
        String hashSenha = hashService.getHashSenha(authDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByUsernameAndSenha(authDTO.username(), hashSenha);
        String token = jwtService.generateJwt(result);

        // inserindo dado para alteracao (evitando a manipulacao de dados)
        ArmacaoDTO dto = new ArmacaoDTO(100.0, "armacao-3", 1, 100, "tamanho", "tipo", "material", 1L, "formato", 1, "curvaDaLente", 1L);
        long id = armacaoService.create(dto).id();
        given()
            .header("Authorization", "Bearer " + token)
            .when()
                .delete("/" + id)
            .then()
                .statusCode(204);
        // verificando se foi apagado no banco de dados
        Armacao armacao = armacaoRepository.findById(id);
        assertNull(armacao);
    }
}