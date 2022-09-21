package com.example.template.repository;

import com.example.template.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Template repository
 */
public interface TemplateRepository extends JpaRepository<Template, Long> {
}
