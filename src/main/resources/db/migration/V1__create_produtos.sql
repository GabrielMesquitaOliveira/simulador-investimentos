CREATE TABLE IF NOT EXISTS produtos (
    id                   INTEGER PRIMARY KEY AUTOINCREMENT,
    nome                 TEXT    NOT NULL,
    tipo_produto         TEXT    NOT NULL,
    rentabilidade_anual  REAL    NOT NULL,
    risco                TEXT    NOT NULL,
    prazo_min_meses      INTEGER NOT NULL,
    prazo_max_meses      INTEGER NOT NULL,
    valor_min            REAL    NOT NULL,
    valor_max            REAL    NOT NULL
);
