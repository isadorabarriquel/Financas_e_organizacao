-- Table: public.usuario

-- DROP TABLE IF EXISTS public.usuario;

CREATE TABLE IF NOT EXISTS public.usuario
(
    id integer NOT NULL,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(16) COLLATE pg_catalog."default" NOT NULL,
    dt_criacao date,
    dt_atualizacao date,
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.usuario
    OWNER to postgres;

-- Table: public.categoria

-- DROP TABLE IF EXISTS public.categoria;

CREATE TABLE IF NOT EXISTS public.categoria
(
    id integer NOT NULL,
    id_usuario integer,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    tipo character varying(50) COLLATE pg_catalog."default",
    dt_criacao date,
    dt_atualizacao date,
    CONSTRAINT categoria_pkey PRIMARY KEY (id),
    CONSTRAINT fk_id_usuario_categoria FOREIGN KEY (id_usuario)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.categoria
    OWNER to postgres;

-- Table: public.conta

-- DROP TABLE IF EXISTS public.conta;

CREATE TABLE IF NOT EXISTS public.conta
(
    id integer NOT NULL,
    id_usuario integer,
    nome character varying(20) COLLATE pg_catalog."default",
    tipo character varying(50) COLLATE pg_catalog."default",
    moeda character varying(15) COLLATE pg_catalog."default",
    saldo numeric(10,2),
    dt_criacao date,
    dt_atualizacao date,
    CONSTRAINT conta_pkey PRIMARY KEY (id),
    CONSTRAINT fk_id_usuario_conta FOREIGN KEY (id_usuario)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.conta
    OWNER to postgres;
-- Table: public.transacao

-- DROP TABLE IF EXISTS public.transacao;

CREATE TABLE IF NOT EXISTS public.transacao
(
    id integer NOT NULL,
    id_usuario integer,
    id_conta integer,
    id_categoria integer,
    tipo character varying(50) COLLATE pg_catalog."default",
    valor numeric(10,2),
    dt_transacao date,
    descricao character varying(100) COLLATE pg_catalog."default",
    dt_criacao date,
    dt_atualizacao date,
    CONSTRAINT transacao_pkey PRIMARY KEY (id),
    CONSTRAINT fk_id_categoria_transacao FOREIGN KEY (id_categoria)
        REFERENCES public.categoria (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_id_conta_transacao FOREIGN KEY (id_conta)
        REFERENCES public.conta (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_id_usuario_transacao FOREIGN KEY (id_usuario)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.transacao
    OWNER to postgres;