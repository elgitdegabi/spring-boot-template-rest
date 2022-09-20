------------------------------------------------------------------------------------------------------------------------
SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE template;
SET REFERENTIAL_INTEGRITY TRUE;

------------------------------------------------------------------------------------------------------------------------
INSERT INTO template(template_id, template_some_col) VALUES(1, 'first some value');
INSERT INTO template(template_id, template_some_col) VALUES(2, 'second some value');
INSERT INTO template(template_id, template_some_col) VALUES(3, 'third some value');

------------------------------------------------------------------------------------------------------------------------