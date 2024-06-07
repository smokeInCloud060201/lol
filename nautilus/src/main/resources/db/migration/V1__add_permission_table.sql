CREATE TABLE IF NOT EXISTS permission
(
    id         BIGINT                      NOT NULL,
    created_at TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    is_deleted BOOLEAN                     NOT NULL,
    name       VARCHAR(30)                 NOT NULL,
    CONSTRAINT pk_permission PRIMARY KEY (id)
);
