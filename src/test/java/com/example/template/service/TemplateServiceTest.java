package com.example.template.service;

import com.example.template.entity.Template;
import com.example.template.exception.TemplateNotFoundException;
import com.example.template.repository.TemplateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.example.template.util.stub.TemplateStub.createTemplate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test class for {@link TemplateService}
 */
@ExtendWith(MockitoExtension.class)
public class TemplateServiceTest {

    @Mock
    private TemplateRepository templateRepository;

    private TemplateService templateService;

    @BeforeEach
    public void setUp() {
        templateService = new TemplateService(templateRepository);
    }

    /**
     * Executes parameterized test cases for {@link TemplateService#getAll()}
     *
     * @param templateList {@link List<Template>}
     * @param expected     {@link List<Template>}
     */
    @ParameterizedTest(name = "template list: {0} - expected: {1}")
    @MethodSource("getAllTestCases")
    void parameterizedGetAllTestCases(final List<Template> templateList, final List<Template> expected) {
        when(templateRepository.findAll()).thenReturn(templateList);
        assertThat(templateService.getAll(), containsInAnyOrder(expected.toArray()));
    }

    /**
     * Executes {@link TemplateService#getTemplate(Long)} with valid {@link Long} id value
     * Expectation: a {@link Template} should be returned
     */
    @Test
    void whenGetTemplateWithValidIdShouldReturnExpectedTemplate() {
        Template expected = createTemplate(1);

        when(templateRepository.findById(any())).thenReturn(Optional.of(createTemplate(1)));

        Assertions.assertEquals(expected, templateService.getTemplate(1L));
    }

    /**
     * Executes {@link TemplateService#getTemplate(Long)} with no valid {@link Long} id value
     * Expectation: a {@link TemplateNotFoundException} should be thrown
     */
    @Test
    void whenGetTemplateWithNoValidIdShouldThrowTemplateNotFoundException() {
        when(templateRepository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(TemplateNotFoundException.class, () -> templateService.getTemplate(1L));
    }

    /**
     * Executes {@link TemplateService#create(Template)} with valid value
     * Expectation: a {@link Template} should be created
     */
    @Test
    void whenCreateTemplateWithValidValuesShouldReturnAddedTemplate() {
        Template expected = createTemplate(1);
        Template newTemplate = createTemplate(1);

        when(templateRepository.save(any())).thenReturn(newTemplate);

        Assertions.assertEquals(expected, templateService.create(newTemplate));
    }

    /**
     * Generates getAll tests cases values
     *
     * @return {@link Stream<Arguments>}
     */
    private static Stream<Arguments> getAllTestCases() {
        return Stream.of(
                arguments(List.of(), Collections.emptyList()),
                arguments(List.of(createTemplate(1), createTemplate(2)), List.of(createTemplate(1), createTemplate(2)))
        );
    }
}
