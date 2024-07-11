CREATE TABLE IF NOT EXISTS email_sent_history
(
    id                     BIGINT                      NOT NULL,
    created_at             TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at             TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    is_deleted             BOOLEAN                     NOT NULL,
    recipients             VARCHAR(30)                 NOT NULL,
    email_sent_status_type VARCHAR(80)                 NOT NULL,
    sent_at                TIMESTAMP(6)               NOT NULL,
    error_message          TEXT                        NOT NULL,
    CONSTRAINT pk_email_sent_history PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS email_sent_template
(
    sent_history_id BIGINT NOT NULL,
    template_id     BIGINT NOT NULL
);
