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