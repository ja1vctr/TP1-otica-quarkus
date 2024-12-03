package br.unitins.topicos1.Service.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArmacaoFileServiceImp implements FileService {
    
    private final String PATH_ARMACAO = System.getProperty("user.home") + 
                                        File.separator + "quarkus" +
                                        File.separator + "imagens" +
                                        File.separator + "armacao" + File.separator;

    private static final List<String> SUPPORTED_MIME_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif");
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10 mb
                                             
    
    @Override
    public String save(String nomeArquivo, byte[] arquivo) throws IOException {
        // Criar os diretórios, se não existirem
        Path diretorio = Paths.get(PATH_ARMACAO);
        Files.createDirectories(diretorio);

        // Identificar o tipo MIME
        String mimeType = Files.probeContentType(Paths.get(nomeArquivo));
        if (mimeType == null || !SUPPORTED_MIME_TYPES.contains(mimeType)) {
            throw new IOException("Tipo de arquivo nao suportado ou não identificado: " + mimeType);
        }

        // Gerar um nome de arquivo único
        String extensao = mimeType.substring(mimeType.lastIndexOf("/") + 1);
        String novoNomeArquivo = UUID.randomUUID() + "." + extensao;

        // Caminho final
        Path filePath = diretorio.resolve(novoNomeArquivo);

        // Tratar duplicação de arquivos (pouco provável com UUID)
        if (filePath.toFile().exists()) {
            throw new IOException("Arquivo já existe: " + novoNomeArquivo);
        }

        // Salvar o arquivo
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(arquivo);
        }

        return novoNomeArquivo;
    }

    @Override
    public File find(String nomeArquivo) {
        try{
            return new File(PATH_ARMACAO + nomeArquivo);
        }catch(Exception e){
            throw new ValidationException("Imagem", e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.home"));
    }
}
