CREATE TABLE IF NOT EXISTS simulacoes (
    id                      INTEGER PRIMARY KEY AUTOINCREMENT,
    cliente_id              INTEGER NOT NULL,
    produto_nome            TEXT    NOT NULL,
    tipo_produto            TEXT    NOT NULL,
    valor_investido         REAL    NOT NULL,
    prazo_meses             INTEGER NOT NULL,
    rentabilidade_aplicada  REAL    NOT NULL,
    valor_final             REAL    NOT NULL,
    data_simulacao          TEXT    NOT NULL
);
