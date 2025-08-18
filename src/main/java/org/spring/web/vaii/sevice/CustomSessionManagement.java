package org.spring.web.vaii.sevice;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@ApplicationScope
public class CustomSessionManagement {
    private Set<String> sessionId = new HashSet<>();


    public synchronized void setSessionId(String sessionId) {
        if (this.sessionId.isEmpty()) {
            this.sessionId.add(sessionId);
        }
    }


    public synchronized void removeSessionId(String sessionId) {
            this.sessionId.remove(sessionId);

    }


    public boolean checkSessionId(String sessionId) {
        return this.sessionId.contains(sessionId);
    }



    public boolean isEmpty() {
        return this.sessionId.isEmpty();
    }
}
