package com.example.template.service;

import com.example.template.entity.Template;
import com.example.template.exception.TemplateNotFoundException;
import com.example.template.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Template service
 */
@Service
public class TemplateService {

    private TemplateRepository templateRepository;

    @Autowired
    public TemplateService(final TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    /**
     * Gets a {@link List} of {@link Template}
     *
     * @return a {@link List<Template>}
     */
    public List<Template> getAll() {
        return templateRepository.findAll();
    }

    /**
     * Gets a {@link Template} from given {@link Long} template's id
     *
     * @param templateId {@link Long} template's id
     * @return a {@link Template} or {@link TemplateNotFoundException} if template does not exist
     */
    public Template getTemplate(final Long templateId) {
        try {
            return templateRepository.findById(templateId).get();
        } catch (Exception e) {
            throw new TemplateNotFoundException("Not found: " + templateId);
        }
    }

    /**
     * Creates a {@link Template}
     *
     * @param template {@link Template}
     * @return created {@link Template}
     */
    public Template create(final Template template) {
        return templateRepository.save(template);
    }
}
