package com.example.template.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Template entity
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Template implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Long id;

    @Column(name = "template_some_col")
    @NotBlank(message = "someCol is mandatory")
    private String someCol;
}
