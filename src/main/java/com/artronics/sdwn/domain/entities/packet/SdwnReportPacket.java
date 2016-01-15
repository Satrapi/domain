package com.artronics.sdwn.domain.entities.packet;

import org.apache.log4j.Logger;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "reports")
@PrimaryKeyJoinColumn(name = "report_id")
public class SdwnReportPacket extends PacketEntity
{
    private final static Logger log = Logger.getLogger(SdwnReportPacket.class);

    private Integer battery;

    public SdwnReportPacket()
    {
    }

    public SdwnReportPacket(List<Integer> content)
    {
        super(content);

        this.battery = SdwnPacketHelper.getBattery(content);
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
