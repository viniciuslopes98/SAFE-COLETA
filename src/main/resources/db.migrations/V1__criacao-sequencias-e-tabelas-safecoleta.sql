-- Criação da sequência para o ID do morador
CREATE SEQUENCE MORADOR_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Criação da sequência
CREATE SEQUENCE CAMINHAO_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Criação da sequência para a tabela de Coleta de Lixo
CREATE SEQUENCE COLETA_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Criação da sequência para a tabela de Agendamentos de Coleta
CREATE SEQUENCE AGENDAMENTO_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Criação da sequência para a tabela de Notificações
CREATE SEQUENCE NOTIFICACAO_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Criação da sequência para a tabela de Usuários
CREATE SEQUENCE USER_SEQ
        START WITH 1
        INCREMENT BY 1
        NOCACHE
        NOCYCLE;

-- Criação da tabela de Moradores
    CREATE TABLE tbl_morador (
        ID INTEGER DEFAULT MORADOR_SEQ.NEXTVAL NOT NULL PRIMARY KEY,
        nome VARCHAR2(100) NOT NULL,
        email VARCHAR2(100) NOT NULL
    );

    -- Criação da tabela de Caminhões
    CREATE TABLE tbl_caminhao (
        ID INTEGER DEFAULT CAMINHAO_SEQ.NEXTVAL NOT NULL PRIMARY KEY,
        placa VARCHAR2(10) NOT NULL,
        motorista VARCHAR2(100) NOT NULL,
        status VARCHAR2(50) NOT NULL,
        ultima_atualizacao TIMESTAMP
    );

    -- Criação da tabela de Coleta de Lixo
    CREATE TABLE tbl_coleta_lixo (
        ID INTEGER DEFAULT COLETA_SEQ.NEXTVAL NOT NULL PRIMARY KEY,
        tipo_residuos VARCHAR2(100) NOT NULL,
        descricao VARCHAR2(255),
        data_coleta DATE NOT NULL,
        horario VARCHAR2(10),
        localizacao VARCHAR2(255)
    );

    -- Criação da tabela de Agendamento coleta
    CREATE TABLE tbl_agendamento_coleta (
        ID INTEGER DEFAULT AGENDAMENTO_SEQ.NEXTVAL NOT NULL PRIMARY KEY,
        tipo_residuos VARCHAR2(100) NOT NULL,
        data_agendamento DATE NOT NULL,
        horario VARCHAR2(10),
        endereco VARCHAR2(255),
        confirmado CHAR(1) CHECK (confirmado IN ('0', '1')),
        caminhao_id NUMBER,
        CONSTRAINT fk_agendamento_caminhao FOREIGN KEY (caminhao_id) REFERENCES tbl_caminhao(ID)
    );

    -- Criação da tabela de Notificação
    CREATE TABLE tbl_notificacao (
        ID INTEGER DEFAULT NOTIFICACAO_SEQ.NEXTVAL NOT NULL PRIMARY KEY,
        mensagem VARCHAR2(255) NOT NULL,
        data_notificacao DATE NOT NULL,
        lida CHAR(1) CHECK (lida IN ('0', '1')),
        morador_id NUMBER,
        CONSTRAINT fk_notificacao_morador FOREIGN KEY (morador_id) REFERENCES tbl_morador(ID)
    );

    -- Criação da tabela de Notificação
    CREATE TABLE TBL_USUARIOS (
        USUARIO_ID INTEGER DEFAULT USER_SEQ.NEXTVAL NOT NULL,
        NOME VARCHAR2(100) NOT NULL,
        EMAIL VARCHAR2(100) UNIQUE NOT NULL,
        SENHA VARCHAR2(150) NOT NULL,
        PAPEL_USUARIO VARCHAR2(50) DEFAULT 'USER'
    );