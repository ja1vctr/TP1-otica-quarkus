-- Criar sequência de IDs para Produto
-- CREATE SEQUENCE produto_seq START WITH 1 INCREMENT BY 1;

-- Inserir na tabela base Cor
INSERT INTO cor ( nome ) VALUES( 'cor-1');
INSERT INTO cor ( nome ) VALUES( 'cor-2');
INSERT INTO cor ( nome ) VALUES( 'cor-3');
INSERT INTO cor ( nome ) VALUES( 'cor-4');
INSERT INTO cor ( nome ) VALUES( 'cor-5');
INSERT INTO cor ( nome ) VALUES( 'cor-6');

-- Inserir na tabela base Marca
INSERT INTO marca ( nome, cnpj ) VALUES('marca-1', 'cnpj-1');
INSERT INTO marca ( nome, cnpj ) VALUES('marca-2', 'cnpj-2');
INSERT INTO marca ( nome, cnpj ) VALUES('marca-3', 'cnpj-3');
INSERT INTO marca ( nome, cnpj ) VALUES('marca-4', 'cnpj-4');
INSERT INTO marca ( nome, cnpj ) VALUES('marca-5', 'cnpj-5');
INSERT INTO marca ( nome, cnpj ) VALUES('marca-6', 'cnpj-6');
INSERT INTO marca ( nome, cnpj ) VALUES('marca-7', 'cnpj-7');
INSERT INTO marca ( nome, cnpj ) VALUES('marca-8', 'cnpj-8');


-- Inseririndo PRODUTOS
-- Inserir na tabela base Usuario
INSERT INTO Usuario(nome, userName, senha, perfil) VALUES ('Joao Victor','joao', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 1);
INSERT INTO Usuario(nome, userName, senha, perfil) VALUES ('user comum','user', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 2);

-- Inserir na tabela base Produto
INSERT INTO produto (id, preco, nome, id_status, quantidade, tamanho, tipo, material, id_marca, nomeimagem, tipo_produto) 
VALUES (nextval('produto_seq'), 100.00, 'armacao-1', 1, 100, 'G', 'Classico', 'Carbono', 1, null, 'ARMACAO');

-- Inserir na tabela específica Armacao
INSERT INTO armacao (id, formato, categoria, curvadalente, id_cor) 
VALUES (currval('produto_seq'), 'Retangulo', 1, 'Suave', 1);

-- Inserir na tabela base Produto
INSERT INTO produto (id, preco, nome, id_status, quantidade, tamanho, tipo, material, id_marca, nomeimagem, tipo_produto) 
VALUES (nextval('produto_seq'), 100.00, 'oculos-1', 1, 100, 'G', 'Classico', 'Carbono', 1, null, 'OCULOS_DE_SOL');

-- Inserir na tabela específica OculosDeSol
INSERT INTO oculosdesol (id, modelo, categoria, filtroSolar, id_cor) 
VALUES (currval('produto_seq'), 'Escrtorio', 1, 'Suave', 1);

-- Inserir na tabela base Produto
INSERT INTO produto (id, preco, nome, id_status, quantidade, tamanho, tipo, material, id_marca, nomeimagem, tipo_produto) 
VALUES (nextval('produto_seq'), 100.00, 'lente-1', 1, 100, 'G', 'Classico', 'Carbono', 1, null, 'LENTE');

-- Inserir na tabela específica Lente
INSERT INTO lente (id, tratamento, espessura, receita) 
VALUES (currval('produto_seq'), 'antireflexo', 'fina', 'receita');


-- Inserir na tabela base Endereco
INSERT INTO endereco (cep, estado, cidade, bairro, rua, numero ) VALUES ('cep-1', 'estado-1', 'cidade-1', 'bairro-1', 'rua-1', 'numero-1' );
INSERT INTO endereco (cep, estado, cidade, bairro, rua, numero ) VALUES ('cep-2', 'estado-2', 'cidade-2', 'bairro-2', 'rua-2', 'numero-2' );