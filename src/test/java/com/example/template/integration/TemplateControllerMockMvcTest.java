package com.example.template.integration;

import com.example.template.entity.Template;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.example.template.util.stub.TemplateStub.createTemplate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * MockMvc test class for {@link com.example.template.controller.TemplateController}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
public class TemplateControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Executes add end-point with valid values
     * Expectation: should return added {@link Template}
     *
     * @throws Exception
     */
    @Test
    public void whenCreateTemplateWithValidAttributesShouldReturnSavedTemplate() throws Exception {
        Template expectedTemplate = createTemplate(1);
        String stringTemplate = objectMapper.writeValueAsString(expectedTemplate);

        String result = mockMvc.perform(post("/template/add").contentType(MediaType.APPLICATION_JSON).content(stringTemplate))
                .andExpect(jsonPath("$.id", comparesEqualTo(1)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals(expectedTemplate, objectMapper.readValue(result, Template.class));
    }

    /**
     * Executes add end-point with no valid values
     * Expectation: should return error {@link String} message
     *
     * @throws Exception
     */
    @Test
    public void whenCreateTemplateWithNoValidAttributesShouldReturnExceptionMessage() throws Exception {
        String stringTemplate = objectMapper.writeValueAsString(new Template());

        String result = mockMvc.perform(post("/template/add").contentType(MediaType.APPLICATION_JSON).content(stringTemplate))
                .andExpect(status().isUnprocessableEntity())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("Error creating template", result);
    }

    /**
     * Executes getAll end-point
     * Expectation: should return a {@link List<Template>}
     *
     * @throws Exception
     */
    @Test
    public void whenGetAllShouldReturnTemplateList() throws Exception {
        List<Template> expectedTemplateList = List.of(createTemplate(1), createTemplate(2), createTemplate(3));

        String result = mockMvc.perform(get("/template/"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Template> resultList = objectMapper
                .readValue(result, objectMapper.getTypeFactory().constructCollectionType(List.class, Template.class));
        assertThat(resultList, containsInAnyOrder(expectedTemplateList.toArray()));
    }

    /**
     * Executes getTemplate end-point with valid id
     * Expectation: should return found {@link Template}
     *
     * @throws Exception
     */
    @Test
    public void whenGetTemplateWithValidIdShouldReturnValidTemplate() throws Exception {
        Template expectedTemplate = createTemplate(1);

        String result = mockMvc.perform(get("/template/1"))
                .andExpect(jsonPath("$.id", comparesEqualTo(1)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals(expectedTemplate, objectMapper.readValue(result, Template.class));
    }

    /**
     * Executes getTemplate end-point with no valid id
     * Expectation: should return not found status and empty response body
     *
     * @throws Exception
     */
    @Test
    public void whenGetTemplateWithNoValidIdShouldReturnExceptionMessage() throws Exception {
        String result = mockMvc.perform(get("/template/99"))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("", result);
    }
}
