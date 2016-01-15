package com.artronics.sdwn.domain.entities.packet;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "data_packets")
@PrimaryKeyJoinColumn(name = "data_id")
public class SdwnDataPacket extends PacketEntity
{
    private final static Logger log = Logger.getLogger(SdwnDataPacket.class);

    private List<Integer> payload;

    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionType(type = "java.util.ArrayList")
    @CollectionTable(name = "packet_payload", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "payload")
    public List<Integer> getPayload()
    {
        return payload;
    }

    public void setPayload(List<Integer> payload)
    {
        this.payload = payload;
    }

}
