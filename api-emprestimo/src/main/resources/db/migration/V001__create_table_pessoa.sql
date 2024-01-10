CREATE TABLE pessoa (
	id bigserial NOT NULL,
	nome varchar(50) NOT NULL,
	identificador varchar(50) NOT NULL,
	data_nascimento date NOT NULL,
	tipo_identificador varchar(50) NOT NULL,
	valor_min_mensal decimal(18, 4) NOT NULL,
	valor_max_emprestimo decimal(18, 4) NOT NULL,
	CONSTRAINT pessoa_pk PRIMARY KEY (id),
    CONSTRAINT pessoa_akey UNIQUE (identificador)
);