CREATE TABLE IF NOT EXISTS token
(
    id                   BIGINT                      NOT NULL,
    created_at           TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at           TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    is_deleted           BOOLEAN                     NOT NULL,
    access_token         TEXT                        NOT NULL,
    refresh_token        TEXT                        NOT NULL,
    token_id             TEXT                        NOT NULL,
    client_id            TEXT                        NOT NULL,
    oauth2_authorization TEXT                                 DEFAULT FALSE,
    CONSTRAINT pk_token PRIMARY KEY (id)
);
