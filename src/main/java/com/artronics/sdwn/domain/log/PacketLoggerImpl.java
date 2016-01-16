package com.artronics.sdwn.domain.log;

import com.artronics.sdwn.domain.entities.packet.Packet;
import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import com.artronics.sdwn.domain.entities.packet.SdwnReportPacket;
import com.artronics.sdwn.domain.helpers.PrintHelper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PacketLoggerImpl implements PacketLogger
{
    private Set<Logger> loggers = new HashSet<>();
    private Logger report;

    @Override
    public void log(PacketEntity packet)
    {
        switch (packet.getType()){
            case REPORT:
                report.debug(logReport((SdwnReportPacket) packet));
                break;
        }
    }

    private String logReport(SdwnReportPacket packet){
        return PrintHelper.printNeighborsOfReportPacket(packet);
    }

    public void logBuffer(Packet packet)
    {
        logBuffer.debug(logBufferLevel(packet));
    }

    private static String logBufferLevel(Packet packet)
    {
        String s = String.format("%-8s", "BUFF");
        s += ":";
        List<Integer> content = packet.getContent();
        for (Integer c : content) {
            s += String.format("%-4d", c);
        }

        return s;
    }

    public void logDevice(PacketEntity packet)
    {
        logDevice.debug(createDeviceLog(packet));
    }

    private static String createDeviceLog(PacketEntity packet){
        String s="Device Packet-> src: "+ packet.getSrcNode() + " dst: " + packet.getDstNode();
        return s;
    }

}
