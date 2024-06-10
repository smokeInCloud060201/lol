CREATE TABLE IF NOT EXISTS token
(
    id                  BIGINT                      NOT NULL,
    created_at          TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at          TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    is_deleted          BOOLEAN                     NOT NULL,
    access_token        VARCHAR(255)                NOT NULL,
    refresh_token       VARCHAR(255)                NOT NULL,
    token_expired       BIGINT                      NOT NULL,
    refresh_token_expired BIGINT                      NOT NULL,
    invoked             BOOLEAN                              DEFAULT FALSE,
    CONSTRAINT pk_token PRIMARY KEY (id)
);
