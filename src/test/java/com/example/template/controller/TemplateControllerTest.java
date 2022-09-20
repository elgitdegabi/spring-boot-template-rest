package com.example.template.controller;

import com.example.template.entity.Template;
import com.example.template.service.TemplateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test class for {@link TemplateController}
 */
@ExtendWith(MockitoExtension.class)
public class TemplateControllerTest {

    @Mock
    private TemplateService templateService;

    private TemplateController templateController;

    /**
     *
     */
    @BeforeEach
    public void setUp() {
        templateController = new TemplateController(templateService);
    }

    /**
     * @param templateList
     * @param expected
     */
    @ParameterizedTest(name = "template list: {0} - expected: {1}")
    @MethodSource("getAllTestCases")
    void parameterizedGetAllTestCases(final List<Template> templateList, final List<Template> expected) {
        when(templateService.getAll()).thenReturn(templateList);
        assertThat(templateController.getAll(), containsInAnyOrder(expected.toArray()));
    }

    /**
     * @param templateId
     * @param template
     * @param expected
     */
    @ParameterizedTest(name = "id: {0} - expected: {2}")
    @MethodSource("getByIdTestCases")
    void parameterizeGetByIdTestCases(final Long templateId, final Template template, final Template expected) {
        when(templateService.getTemplate(any())).thenReturn(template);
        Assertions.assertEquals(expected, templateController.getById(templateId));
    }

    /**
     *
     */
    @Test
    void whenCreateTemplateWithValidValuesShouldReturnAddedTemplate() {
        Template expected = createTemplate(1);
        Template newTemplate = createTemplate(1);

        when(templateService.create(any())).thenReturn(newTemplate);

        Assertions.assertEquals(expected, templateController.create(newTemplate));
    }

    /**
     *
     */
    @Test
    void whenHandleMethodArgumentNotValidShouldReturnAnExceptionMessage() {
        MethodParameter methodParameter = mock(MethodParameter.class);
        BindingResult bindingResult = mock(BindingResult.class);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(methodParameter, bindingResult);

        when(bindingResult.getFieldErrors()).thenReturn(Collections.emptyList());

        Assertions.assertEquals("Error creating template", templateController.handleMethodArgumentNotValid(exception));
    }

    /**
     * @param id
     * @return
     */
    private static Template createTemplate(final long id) {
        Template template = new Template();
        template.setId(id);
        template.setSomeCol("some col " + id);

        return template;
    }

    /**
     * Generates getAll tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> getAllTestCases() {
        return Stream.of(
                arguments(List.of(), Collections.emptyList()),
                arguments(List.of(createTemplate(1), createTemplate(2)), List.of(createTemplate(1), createTemplate(2)))
        );
    }

    /**
     * Generates getById tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> getByIdTestCases() {
        return Stream.of(
                arguments(999L, new Template(), new Template()),
                arguments(1L, createTemplate(1), createTemplate(1))
        );
    }
}
