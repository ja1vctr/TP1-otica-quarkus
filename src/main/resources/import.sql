-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


insert into cor (indice, nome ) values( 'xxx1', 'cor-teste-1');
insert into cor (indice, nome ) values( 'xxx2', 'cor-teste-2');
insert into cor (indice, nome ) values( 'xxx3', 'cor-teste-3');
insert into cor (indice, nome ) values( 'xxx4', 'cor-teste-4');
insert into cor (indice, nome ) values( 'xxx5', 'cor-teste-5');
insert into cor (indice, nome ) values( 'xxx6', 'cor-teste-6');

insert into marca (nome) values('marca-teste-1');
insert into marca (nome) values('marca-teste-2');
insert into marca (nome) values('marca-teste-3');
insert into marca (nome) values('marca-teste-4');
insert into marca (nome) values('marca-teste-5');
insert into marca (nome) values('marca-teste-6');
insert into marca (nome) values('marca-teste-7');
insert into marca (nome) values('marca-teste-8');

insert into armacao (id, preco, nome, status, material, tamanho, formato, curvaLente, modelo, id_cor, id_marca) values(1, 100.00, 'armacao-teste-1', 1, 'Carbono', 'M', 'formato1', 'Sem curva', 'modelo1', 1,1);
insert into armacao (id, preco, nome, status, material, tamanho, formato, curvaLente, modelo, id_cor, id_marca) values(2, 200.00, 'armacao-teste-2', 0, 'Plastico', 'G', 'formato1', 'Sem curva', 'modelo1', 1,1);