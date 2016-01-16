package com.artronics.sdwn.domain.helpers;

import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.map.BaseGraphTest;
import org.junit.Test;

public class SdwnNetMapPrinterTest extends BaseGraphTest
{
    private static final NetworkMapPrinter<SdwnNodeEntity> printer= new SdwnNetMapPrinter();

    @Test
    public void just_print_a_netMap_to_see_the_result(){
        String s =printer.printNetworkMap(networkMap);
        System.out.println(s);
    }

    @Test
    public void print_with_specific_device(){
        String s =printer.printNetworkMap(networkMap, seeder.getDevice1());
        System.out.println(s);

    }

}