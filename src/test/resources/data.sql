------------------------------------------------------------------------------------------------------------------------
SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE template;
SET REFERENTIAL_INTEGRITY TRUE;

------------------------------------------------------------------------------------------------------------------------
INSERT INTO template(template_id, template_some_col) VALUES(1, 'some col 1');
INSERT INTO template(template_id, template_some_col) VALUES(2, 'some col 2');
INSERT INTO template(template_id, template_some_col) VALUES(3, 'some col 3');

------------------------------------------------------------------------------------------------------------------------