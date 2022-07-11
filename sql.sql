create table if not exists categorias(
	id BIGINT not null AUTO_INCREMENT
    , nome varchar(100) not null unique
    , ativo boolean not null
    , PRIMARY KEY (id) 
    );

create table if not exists produtos(
	id BIGINT not null AUTO_INCREMENT
    , nome varchar(100) not null
    , descricao varchar(250) not null
    , preco_unitario decimal(10,4) not null
    , quantidade_estoque int not null
    , categoria_id bigint not null
    , PRIMARY KEY(id)
    , FOREIGN KEY (categoria_id) REFERENCES categorias(id) ON DELETE CASCADE
    );
    
create table if not exists usuarios(
	id BIGINT not null AUTO_INCREMENT
    , nome varchar(100) not null
    , email varchar(250) not null unique
    , senha varchar(250) not null
    , PRIMARY KEY(id)
    );
    
create table if not exists perfis(id BIGINT not null auto_increment, nome varchar(250) not null, primary key(id));
create table if not exists clientes(
	id BIGINT NOT NULL AUTO_INCREMENT
    , nome varchar(250) not null
    , cpf varchar(250) not null
    , rua varchar(250) not null
    , numero varchar(250) not null
    , complemento varchar(250)
    , bairro varchar(250) not null
    , cidade varchar(250) not null
    , estado varchar(250) not null
    , ddd int(2) not null
    , numeroTelefone int(9) not null
    , usuario_id bigint not null
    , primary key(id)
    , FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

create table if not exists pedidos(
	id bigint auto_increment not null
    , data_pedido datetime DEFAULT CURRENT_TIMESTAMP not null
    , desconto decimal(10,4) not null
    , tipo_desconto varchar(50) not null
    , valor_total decimal(10,4) not null
    , cliente_id bigint not null
    , primary key(id)
    , foreign key (cliente_id) references clientes(id) ON DELETE CASCADE
);

create table if not exists itens_pedido(
	id bigint auto_increment not null
    , preco_unitario decimal(10,4) not null
    , quantidade int not null
    , produto_id bigint not null
    , pedido_id bigint not null
    , desconto decimal(10,4) not null
    , tipo_desconto varchar(50) not null
    , foreign key (produto_id) references produtos(id) ON DELETE CASCADE
    , foreign key (pedido_id) references pedidos(id) ON DELETE CASCADE
    , primary key(id)
);

create table if not exists usuarios_perfis(
	usuario_id bigint not null
    , perfis_id bigint not null
    , foreign key (usuario_id) references usuarios(id) ON DELETE CASCADE
	, foreign key (perfis_id) references perfis(id) ON DELETE CASCADE
);