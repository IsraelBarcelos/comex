create table categorias(
BigInt id unsigned primary_key AUTO_INCREMENT,
Varchar(250) nome not null,
bit status not null
)

create table produtos(
BigInt id unsigned primary_key AUTO_INCREMENT,
varchar(250) descricao nullable,
varchar(250) nome not null,
preco_unitario decimal(10,4) not null,
quantidade_estoque int not null,
)

create table clientes(
BigInt id unsigned primary_key AUTO_INCREMENT,
Varchar(250) nome not null,
Varchar(250) cpf not null,
Varchar(11) telefone not null,
Varchar(100) rua not null,
varchar(6) numero not null,
varchar(100) complemento nullable,
varchar(50) bairro not null,
varchar(50) cidade not null,
varchar(50) estado not null
)

create table pedidos
(
BigInt id unsigned primary_key AUTO_INCREMENT,
Date data not null,
BigInt cliente_id not null,
decimal(10,4) desconto  not null,
varchar(50) tipo_desconto -- (FIDELIDADE ou NENHUM)
FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
)

create table item_pedido
(
BigInt id,
decimal(10,4) preco_unitario not null,
int quantidade not null,
BigInt pedido_id not null,
BigInt produto_id not null,
decimal(10,4) desconto not null,
varchar(50) tipo_desconto not null -- (QUANTIDADE, PROMOCAO ou NENHUM)
FOREIGN KEY (pedido_id) REFERENCES pedidos(id) ON DELETE CASCADE
FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
)