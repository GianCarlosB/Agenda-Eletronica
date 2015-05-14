/* Cria as tabelas */

CREATE TABLE IF NOT EXISTS pessoa (
	codigopessoa INT NOT NULL,
	nome VARCHAR(250),
	datadeaniversario DATE,
	
	CONSTRAINT pk_pessoa PRIMARY KEY (codigopessoa)
);

CREATE TABLE IF NOT EXISTS email (
	email VARCHAR(250),
	codigopessoa INT NOT NULL,
	
	CONSTRAINT pk_email PRIMARY KEY (email),
	CONSTRAINT fk_email_pessoa FOREIGN KEY (codigopessoa) REFERENCES pessoa(codigopessoa) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS telefone (
	numerotelefone VARCHAR(13),
	tipo CHAR,
	codigopessoa INT NOT NULL,
	
	CONSTRAINT pk_telefone PRIMARY KEY (numerotelefone),
	CONSTRAINT fk_telefone_pessoa FOREIGN KEY (codigopessoa) REFERENCES pessoa(codigopessoa) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tarefa (
	codigotarefa INT NOT NULL,
	descricao VARCHAR (250),  
	data DATE,
	hora TIME,
	
	CONSTRAINT pk_tarefa PRIMARY KEY (codigotarefa)
);
	
CREATE TABLE IF NOT EXISTS compromisso (
	codigopessoa INT NOT NULL,
	codigotarefa INT NOT NULL,

	CONSTRAINT pk_compromisso PRIMARY KEY (codigopessoa, codigotarefa),
	CONSTRAINT fk_compromisso_pessoa FOREIGN KEY (codigopessoa) REFERENCES pessoa(codigopessoa) ON DELETE CASCADE,
	CONSTRAINT fk_compromisso_tarefa FOREIGN KEY (codigotarefa) REFERENCES tarefa(codigotarefa) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS configuracoes (
	idconfiguracao INTEGER NOT NULL,
	lookandfeel NUMERIC(1, 0) NOT NULL DEFAULT 1,
	perspectivapadrao boolean DEFAULT true,
	papeldeparede NUMERIC(1, 0) NOT NULL DEFAULT 4,
	
	CONSTRAINT pk_configuracoes PRIMARY KEY (idconfiguracao)
);

/* Cria Sequencias */
CREATE SEQUENCE seq_pessoa START WITH 1;
CREATE SEQUENCE seq_tarefa START WITH 1;
