package org.spring.web.vaii.email;

import java.util.Map;

public interface EmailTemplate
{
    String getSubject();
    String renderBody(Map<String, Object> variables);
}
