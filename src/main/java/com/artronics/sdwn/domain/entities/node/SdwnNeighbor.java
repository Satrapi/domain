package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.packet.SdwnPacketHelper;
import com.artronics.sdwn.domain.entities.packet.SdwnReportPacket;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class SdwnNeighbor implements Neighbor<SdwnNodeEntity>,Serializable
{
    public final static int NEIGHBOR_INDEX=13;

    private SdwnNodeEntity node;

    private Integer rssi;

    private Double weight;

    public SdwnNeighbor()
    {
    }

    public SdwnNeighbor(SdwnNodeEntity node,
                        Double weight,Integer rssi)
    {
        this.node = node;
        this.rssi = rssi;
        this.weight = weight;
    }

    public SdwnNeighbor(SdwnNodeEntity node, Double weight)
    {
        this.node = node;
        this.weight = weight;
    }

    public SdwnNeighbor(SdwnNodeEntity node, Integer rssi)
    {
        this.node = node;
        this.rssi = rssi;
    }

    public SdwnNeighbor(SdwnNodeEntity node, Integer rssi, Double weight)
    {
        this.node = node;
        this.rssi = rssi;
        this.weight = weight;
    }

    public static List<SdwnNeighbor> createNeighbors(List<Integer> content){
        List<SdwnNeighbor> neighbors = new ArrayList<>();

        for (int i = NEIGHBOR_INDEX; i < content.size(); i += 3) {
            int add = SdwnPacketHelper.joinAddresses(content.get(i),
                                                     content.get(i + 1));
            int rssi = content.get(i + 2);
            SdwnNodeEntity node = new SdwnNodeEntity(Integer.toUnsignedLong(add));
            SdwnNeighbor neighbor = new SdwnNeighbor(node,rssi);
            neighbors.add(neighbor);
        }

        return neighbors;
    }

    public static List<SdwnNeighbor> createNeighbors(SdwnReportPacket packet)
    {
        List<Integer> content = packet.getContent();

        return createNeighbors(content);
    }

    @Override
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "node_id")
    public SdwnNodeEntity getNode()
    {
        return node;
    }

    public void setNode(SdwnNodeEntity node)
    {
        this.node = node;
    }

    @Override
    @Column(name = "weight",nullable = false,unique = false)
    public Double getWeight()
    {
        return weight;
    }

    public void setWeight(Double weight)
    {
        this.weight = weight;
    }

    @Column(name = "rssi",nullable = false,unique = false)
    public Integer getRssi()
    {
        return rssi;
    }

    public void setRssi(Integer rssi)
    {
        this.rssi = rssi;
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.node);

        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof SdwnNeighbor))
            return false;
        if (obj == this)
            return true;

        SdwnNeighbor that = (SdwnNeighbor) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.node,that.node);

        return eb.isEquals();
    }

    @Override
    public String toString()
    {
        return formatNeighbor(this.weight,this.node);
    }
    public static String formatNeighbor( Double weight, SdwnNodeEntity n2)
    {
        String s ="";
        s+=String.format(" <---[ %-5.0f ]---> " ,weight);
        s += n2.toString();
        return s;
    }
}
