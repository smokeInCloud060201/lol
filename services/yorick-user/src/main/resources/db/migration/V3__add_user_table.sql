CREATE TABLE IF NOT EXISTS users
(
    id                        BIGINT                      NOT NULL,
    created_at                TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at                TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    is_deleted                BOOLEAN                              DEFAULT FALSE,
    email                     VARCHAR(80)                 NOT NULL,
    mobile_no                 VARCHAR(20),
    password                  VARCHAR(80)                 NOT NULL,
    is_verified_email         BOOLEAN                              DEFAULT FALSE,
    is_verified_mobile_no     BOOLEAN                              DEFAULT FALSE,
    is_account_non_expired    BOOLEAN                              DEFAULT TRUE,
    is_account_non_locked     BOOLEAN                              DEFAULT TRUE,
    is_credential_non_expired BOOLEAN                              DEFAULT TRUE,
    is_enabled                BOOLEAN                              DEFAULT TRUE,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_role
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);
