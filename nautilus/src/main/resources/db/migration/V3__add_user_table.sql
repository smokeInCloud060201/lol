CREATE TABLE IF NOT EXISTS users
(
    id         BIGINT                      NOT NULL,
    created_at TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    is_deleted BOOLEAN                     NOT NULL,
    email      VARCHAR(80)                 NOT NULL,
    password   VARCHAR(80)                 NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS role_users
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);
