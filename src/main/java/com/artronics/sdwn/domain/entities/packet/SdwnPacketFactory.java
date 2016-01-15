package com.artronics.sdwn.domain.entities.packet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SdwnPacketFactory implements PacketFactory
{
    private final static Logger log = Logger.getLogger(SdwnPacketFactory.class);

    @Override
    public Packet create(List<Integer> content)
    {
        Packet.Type type = SdwnPacketHelper.getType(content);
        switch (type) {
            case REPORT:
                return new SdwnReportPacket(content);
        }

        return null;
    }
}
