package com.artronics.sdwn.domain.log;

import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import com.artronics.sdwn.domain.entities.packet.SdwnReportPacket;
import com.artronics.sdwn.domain.helpers.PrintHelper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PacketLoggerImpl implements PacketLogger
{
    private static final String BASE = "com.artronics.sdwn.logger.packet";
    private static Logger REPORT = Logger.getLogger(BASE+".report");
    private static Logger BUFF = Logger.getLogger(BASE+".buffer");
    private Class<?> clazz;

    public PacketLoggerImpl()
    {
    }

    public PacketLoggerImpl(Class<?> clazz)
    {
        this.clazz = clazz;
        REPORT = Logger.getLogger(BASE+".report"+clazz.getSimpleName());
    }

    @Override
    public void log(PacketEntity packet)
    {
        BUFF.debug(logBuff(packet));
        switch (packet.getType()){
            case REPORT:
                REPORT.debug(logReport((SdwnReportPacket) packet));
                break;
        }

    }

    private String logReport(SdwnReportPacket packet){
        return PrintHelper.printNeighborsOfReportPacket(packet);
    }

    private static String logBuff(PacketEntity packet)
    {
        String s = "BUFF: ";
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
