package com.example.template.integration;

import com.example.template.controller.TemplateController;
import com.example.template.entity.Template;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.template.util.stub.TemplateStub.createTemplate;

/**
 * Integration test class for Template flow
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TemplateFlowIT {

    @Autowired
    private TemplateController templateController;

    /**
     * Executes Template flow with valid values and retrieves a list of persisted templates
     * Expectation: should return {@link List<Template>}
     */
    @Test
    public void whenCreateTemplateListWithValidAttributesShouldReturnSavedTemplateList() {
        List<Template> templateList = List.of(createTemplate(1), createTemplate(2));
        templateList.stream().forEach(template -> templateController.create(template));

        Assertions.assertArrayEquals(templateList.toArray(), templateController.getAll().toArray());
    }

    /**
     * Executes Template flow with valid values and retrieves a one of persisted templates
     * Expectation: should return a {@link Template}
     */
    @Test
    public void whenCreateTemplateListWithValidAttributesShouldReturnTemplateById() {
        List<Template> templateList = List.of(createTemplate(1), createTemplate(2));
        templateList.stream().forEach(template -> templateController.create(template));

        Assertions.assertEquals(templateList.get(0), templateController.getById(templateList.get(0).getId()));
        Assertions.assertEquals(templateList.get(1), templateController.getById(templateList.get(1).getId()));
    }
}
