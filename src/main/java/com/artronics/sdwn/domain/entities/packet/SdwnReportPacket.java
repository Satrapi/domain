package com.artronics.sdwn.domain.entities.packet;

import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reports")
@PrimaryKeyJoinColumn(name = "id")
public class SdwnReportPacket extends PacketEntity
{
    private final static Logger log = Logger.getLogger(SdwnReportPacket.class);

    protected List<SdwnNeighbor> neighbors=new ArrayList<>();

    private Integer battery;

    public SdwnReportPacket()
    {
    }

    public SdwnReportPacket(List<Integer> content)
    {
        super(content);

        this.neighbors = SdwnNeighbor.createNeighbors(content);
        this.neighbors.forEach(neighbor -> neighbor.setReportPacket(this));

        this.battery = SdwnPacketHelper.getBattery(content);
    }

    @OneToMany(fetch = FetchType.EAGER)//,mappedBy = "reportPacket")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public List<SdwnNeighbor> getNeighbors()
    {
        return neighbors;
    }

    public void setNeighbors(
            List<SdwnNeighbor> neighbors)
    {
        this.neighbors = neighbors;
    }

    public void addNeighbor(SdwnNeighbor neighbor){
        this.neighbors.add(neighbor);
    }

    public Integer getBattery()
    {
        return battery;
    }

    public void setBattery(Integer battery)
    {
        this.battery = battery;
    }
}
