package com.artronics.sdwn.domain.session;

import com.artronics.sdwn.domain.entities.NetworkSession;

public interface SessionManager
{
    NetworkSession open();

    void close() throws Exception;
}
