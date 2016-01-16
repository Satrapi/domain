package com.artronics.sdwn.domain.entities.node;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class SdwnNodeEntityTest
{
    DeviceConnectionEntity device =new DeviceConnectionEntity("foo");
    DeviceConnectionEntity otherDevice =new DeviceConnectionEntity("bar");

    SdwnNodeEntity aNode = new SdwnNodeEntity(10L);
    SdwnNodeEntity sameNode = new SdwnNodeEntity(10L);
    SdwnNodeEntity otherNode = new SdwnNodeEntity(11L);

    @Before
    public void setUp() throws Exception
    {
        device.setId(100L);
        otherDevice.setId(200L);

        aNode.setDevice(device);
        sameNode.setDevice(device);
        otherNode.setDevice(device);
    }

    @Test
    public void test_equals_general(){
        assertTrue(aNode.equals(aNode));
        assertThat(aNode,equalTo(sameNode));
        assertThat(sameNode,equalTo(aNode));

        assertFalse(aNode.equals(otherNode));
        assertFalse(otherNode.equals(aNode));

        SdwnNodeEntity otherSameNode = new SdwnNodeEntity(aNode.getAddress(),device);
        assertThat(sameNode,equalTo(otherSameNode));

        SdwnNodeEntity nullNode = null;
        assertFalse(aNode.equals(nullNode));
    }
    
    @Test
    public void test_not_equals_with_diff_device_id(){
        sameNode.setDevice(otherDevice);
        assertFalse(aNode.equals(sameNode));
    }

    @Test
    public void test_hashCode(){
        assertThat(aNode.hashCode(),equalTo(sameNode.hashCode()));
    }

    @Test
    public void test_set(){
        Set<SdwnNodeEntity> nodes = new HashSet<>();
        nodes.add(aNode);
        nodes.add(sameNode);

        SdwnNodeEntity otherSameNode = new SdwnNodeEntity(aNode.getAddress(),device);

        assertThat(nodes.size(),equalTo(1));
        assertTrue(nodes.contains(otherSameNode));

        otherNode.setAddress(aNode.getAddress());
        otherNode.setDevice(otherDevice);
        nodes.add(otherNode);

        assertThat(nodes.size(),equalTo(2));
    }

}