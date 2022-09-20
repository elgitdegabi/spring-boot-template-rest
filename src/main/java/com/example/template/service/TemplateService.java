package com.example.template.service;

import com.example.template.entity.Template;
import com.example.template.exception.TemplateNotFoundException;
import com.example.template.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class TemplateService {

    private TemplateRepository templateRepository;

    @Autowired
    public TemplateService(final TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    /**
     * @return
     */
    public List<Template> getAll() {
        return templateRepository.findAll();
    }

    /**
     * @param templateId
     * @return
     */
    public Template getTemplate(final Long templateId) {
        try {
            return templateRepository.findById(templateId).get();
        } catch (Exception e) {
            throw new TemplateNotFoundException("Not found: " + templateId);
        }
    }

    /**
     * @param template
     * @return
     */
    public Template create(final Template template) {
        return templateRepository.save(template);
    }
}
