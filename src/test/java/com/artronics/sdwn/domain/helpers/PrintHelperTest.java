package com.artronics.sdwn.domain.helpers;

import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.entities.packet.SdwnReportPacket;
import com.artronics.sdwn.domain.log.PacketLogger;
import com.artronics.sdwn.domain.log.PacketLoggerImpl;
import org.junit.Before;
import org.junit.Test;

public class PrintHelperTest
{
    PacketLogger logger = new PacketLoggerImpl();

    protected static final PrintHelper printer = new PrintHelper();
    FakePacketFactory f = new FakePacketFactory();

    protected SdwnNodeEntity src = new SdwnNodeEntity(2123L);
    protected SdwnNodeEntity dst = new SdwnNodeEntity(87L);
    protected SdwnNodeEntity n0 = new SdwnNodeEntity(10L);
    protected SdwnNodeEntity n1 = new SdwnNodeEntity(143L);

    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void test_short_and_long_node_toString(){
        System.out.println(PrintHelper.printLongNode(src));
        System.out.println(PrintHelper.printShortNode(src));
    }

    @Test
    public void test_print_neighbors_of_report(){
        SdwnReportPacket packet = f.createReportPacket(1L, src, dst, null, n0, n1);

        System.out.println(PrintHelper.printNeighborsOfReportPacket(packet));
    }

    @Test
    public void test_packet_logger(){
        SdwnReportPacket packet = f.createReportPacket(1L, src, dst, null, n0, n1);
        logger.log(packet);
    }

}