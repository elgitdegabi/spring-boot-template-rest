package com.example.template.util.stub;

import com.example.template.entity.Template;

/**
 * Stub class for {@link Template}
 */
public class TemplateStub {

    /**
     * Creates a stub {@link Template} from given values
     *
     * @param id {@link Long}
     * @return created stub {@link Template}
     */
    public static Template createTemplate(final long id) {
        Template template = new Template();
        template.setId(id);
        template.setSomeCol("some col " + id);

        return template;
    }
}
