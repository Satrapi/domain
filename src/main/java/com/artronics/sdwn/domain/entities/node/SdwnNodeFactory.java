package com.artronics.sdwn.domain.entities.node;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SdwnNodeFactory implements NodeFactory
{
    private final static Logger log = Logger.getLogger(SdwnNodeFactory.class);

    protected Integer battery;

    @Override
    public SdwnNodeEntity create(Long address)
    {
        return null;
    }
}
