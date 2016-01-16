package com.artronics.sdwn.domain.helpers;

import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.entities.packet.SdwnReportPacket;
import org.apache.log4j.Logger;

import java.util.List;

public class PrintHelper
{
    private final static Logger log = Logger.getLogger(PrintHelper.class);

    public static String printShortNode(SdwnNodeEntity node){
        String s = node.getType() == SdwnNodeEntity.Type.SINK ? "Sink-> " : "Node-> ";
        return String.format(s + "ADD:%-3d ", node.getAddress());

    }

    public static String printLongNode(SdwnNodeEntity node){
        String s = printShortNode(node);
        String f = node.getId()==null? "%-5s":"%-5d";
        return String.format(s+"[%-7s] ID:"+f, node.getStatus(),
                             node.getId()==null? "UNK":node.getId());

    }

    public static String printNeighborsOfReportPacket(SdwnReportPacket packet){

        boolean wr = packet.getId()!=null;
        String wOrRssi = wr? "WEIGHT":"RSSI";
        String wrFormat = wr?"%-6.0f":"%-6d";

        String n =String.format("NEIGHBORS:  SRC: %s\n",printLongNode(packet.getSrcNode()) );
        List<SdwnNeighbor> neighbors = packet.getNeighbors();
        int p = n.indexOf("NEIGHBORS:");
        n+=addSpace(p+11);
        n+=String.format("\\ [%6s]\n",wOrRssi);
        if (neighbors.isEmpty()){
            n+="NO NEIGHBORS";
            return n;
        }

        for (SdwnNeighbor neighbor : neighbors) {
            n+=addSpace(p+11);
            n+=String.format(" -["+wrFormat+"] ",wr?neighbor.getWeight():neighbor.getRssi());
            n+=neighbor.getNode().toString();
            n+="\n";
        }

        return n;
    }

    private static String addSpace(int n){
        String s ="";
        for (int i = 0; i < n; i++) {
            s+=" ";
        }

        return s;
    }
}
