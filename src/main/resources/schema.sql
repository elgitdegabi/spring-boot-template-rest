------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS
    TEMPLATE (
        template_id NUMBER(10) NOT NULL,
        template_some_col VARCHAR2(20) NOT NULL,
        template_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        PRIMARY KEY (template_id)
    );

------------------------------------------------------------------------------------------------------------------------