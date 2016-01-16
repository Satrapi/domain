package com.artronics.sdwn.domain.log;

import com.artronics.sdwn.domain.entities.packet.Packet;
import com.artronics.sdwn.domain.entities.packet.PacketEntity;
import com.artronics.sdwn.domain.entities.packet.SdwnReportPacket;
import org.apache.log4j.Logger;

public interface PacketLogger
{
    Logger logBuffer = Logger.getLogger("com.artronics.sdwn.logger.packet.buffer");
    Logger logDevice = Logger.getLogger("com.artronics.sdwn.logger.packet.device");
    Logger logDeviceReport = Logger.getLogger("com.artronics.sdwn.logger.packet.device.report");

    void logBuffer(Packet packet);
    void logDevice(PacketEntity packet);
    void logReport(SdwnReportPacket packet);
}
