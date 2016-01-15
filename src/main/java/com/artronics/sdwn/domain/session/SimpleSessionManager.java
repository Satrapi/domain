package com.artronics.sdwn.domain.session;

import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.repositories.SessionRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("simpleSessionManager")
public class SimpleSessionManager implements SessionManager,DisposableBean
{
    private final static Logger log = Logger.getLogger(SimpleSessionManager.class);

    private SessionRepo sessionRepo;

    @Override
    public NetworkSession open()
    {
        NetworkSession s = sessionRepo.findActiveSession();
        if (s == null) {
            s = new NetworkSession();
            sessionRepo.save(s);

            log.debug("New session has been created." +s.getId());

            return s;
        }

        log.debug("Found active session. "+s);

        return s;
    }

    @Override
    public void close() throws Exception
    {
    }

    @Override
    public void destroy() throws Exception
    {
    }

    @Autowired
    public void setSessionRepo(SessionRepo sessionRepo)
    {
        this.sessionRepo = sessionRepo;
    }

}
