package com.example.template.controller;

import com.example.template.entity.Template;
import com.example.template.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Template controller
 */
@RestController
@RequestMapping("/template")
@Slf4j
public class TemplateController {

    private final TemplateService templateService;

    @Autowired
    public TemplateController(final TemplateService templateService) {
        this.templateService = templateService;
    }

    /**
     * Gets a {@link List} of {@link Template}
     * IMPORTANT: http://localhost:9200/template/
     *
     * @return a {@link List<Template>}
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Template> getAll() {
        return templateService.getAll();
    }

    /**
     * Gets a {@link Template} from given {@link Long} template's id
     * IMPORTANT: http://localhost:9200/template/1
     *
     * @param templateId {@link Long} template's id
     * @return a {@link Template}
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Template getById(@PathVariable("id") final Long templateId) {
        return templateService.getTemplate(templateId);
    }

    /**
     * Creates a {@link Template}
     * IMPORTANT: http://localhost:9200/template/add
     *
     * @param template {@link Template}
     * @return created {@link Template}
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Template create(@Valid @RequestBody Template template) {
        return templateService.create(template);
    }

    /**
     * Handles {@link MethodArgumentNotValidException} thrown
     * IMPORTANT: this method could be implemented as part of an abstract controller and inherited by others controllers
     * that require common exception handlers
     *
     * @param exception {@link MethodArgumentNotValidException}
     * @return {@link String} error message
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        exception.getBindingResult().getFieldErrors().forEach(error -> log.error(error.getField() + " - " + error.getDefaultMessage()));

        return "Error creating template";
    }
}
