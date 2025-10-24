create table usuario(
	id integer not null primary key,
	nome varchar(50) not null,
	email varchar(100) not null,
	senha varchar(16) not null,
	dt_criacao date,
	dt_atualizacao date
);

create table conta(
	id integer not null primary key,
	id_usuario integer,
	nome varchar(20),
	tipo varchar(20),
	moeda varchar(15),
	saldo numeric(10,2),
	dt_criacao date, 
	dt_atualizacao date,
	constraint fk_id_usuario_conta foreign key (id_usuario) references usuario(id)
);

create table categoria(
	id integer not null primary key,
	id_usuario integer, 
	nome varchar(20) not null,
	tipo varchar(20),
	dt_criacao date,
	dt_atualizacao date,
	constraint fk_id_usuario_categoria foreign key (id_usuario) references usuario(id)
);

create table transacao(
	id integer not null primary key,
	id_usuario integer,
	id_conta integer, 
	id_categoria integer,
	tipo varchar(20),
	valor numeric(10,2),
	dt_transacao date, 
	descricao varchar(50),
	dt_criacao date,
	dt_atualizacao date,
	constraint fk_id_usuario_transacao foreign key (id_usuario) references usuario(id),
	constraint fk_id_conta_transacao foreign key (id_conta) references conta(id),
	constraint fk_id_categoria_transacao foreign key (id_categoria) references categoria(id)
);
