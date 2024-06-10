CREATE TABLE IF NOT EXISTS role
(
    id         BIGINT                      NOT NULL,
    created_at TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    is_deleted BOOLEAN                     NOT NULL,
    name       VARCHAR(30)                 NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS role_permission
(
    role_id       BIGINT NOT NULL,
    permission_id BIGINT NOT NULL
);
