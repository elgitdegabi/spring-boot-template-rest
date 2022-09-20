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
     * http://localhost:9200/template/
     *
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Template> getAll() {
        return templateService.getAll();
    }

    /**
     * http://localhost:9200/template/1
     *
     * @param templateId
     * @return
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Template getById(@PathVariable("id") final Long templateId) {
        return templateService.getTemplate(templateId);
    }

    /**
     * http://localhost:9200/template/1
     *
     * @param template
     * @return
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Template create(@Valid @RequestBody Template template) {
        return templateService.create(template);
    }

    /**
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ex.getBindingResult().getFieldErrors().forEach(error -> log.error(error.getField() + " - " + error.getDefaultMessage()));

        return "Error creating template";
    }
}
