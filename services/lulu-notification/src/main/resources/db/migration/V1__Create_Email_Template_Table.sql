CREATE TABLE IF NOT EXISTS email_template
(
    id            BIGINT                      NOT NULL,
    created_at    TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at    TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    is_deleted    BOOLEAN                     NOT NULL,
    template_id   VARCHAR(30)                 NOT NULL,
    template_name VARCHAR(80)                 NOT NULL,
    subject       VARCHAR(80)                 NOT NULL,
    content       TEXT                        NOT NULL,
    CONSTRAINT pk_email_template PRIMARY KEY (id)
);
