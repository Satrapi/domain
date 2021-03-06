package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.entities.SdwnControllerEntity;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.helpers.SeedNetworkGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        BaseRepoTest.BaseRepoConfig.class
})
@ActiveProfiles("test")
public class BaseRepoTest
{
    protected final static String URL = "http://foo.com:8080";
    protected final static String DEVICE_URL = "http://device.com:7840";
    protected final static Long SINK_ADD = 10L;

    @Autowired
    protected SeedNetworkGraph seeder ;

    protected SdwnControllerEntity controller;
    protected DeviceConnectionEntity device;
    protected DeviceConnectionEntity device2;
    protected NetworkSession session;

    protected SdwnNodeEntity node0  ;
    protected SdwnNodeEntity node1  ;
    protected SdwnNodeEntity node30 ;
    protected SdwnNodeEntity node135;
    protected SdwnNodeEntity node136;
    protected SdwnNodeEntity node137;
    protected SdwnNodeEntity node245;
    protected SdwnNodeEntity node246;

    @Autowired
    protected SdwnControllerRepo controllerRepo;

    @Autowired
    protected DeviceConnectionRepo deviceConnectionRepo;

    @Autowired
    protected NodeRepo nodeRepo;

//    @Autowired
//    protected NeighborRepo neighborRepo;

    @Autowired
    protected PacketRepo packetRepo;

    @Autowired
    protected SessionRepo sessionRepo;

    @Before
    @Transactional
    @Rollback(value = false)
    public void setUp() throws Exception
    {
        device =seeder.getDevice1();
        device2 =seeder.getDevice2();

        session = seeder.getActiveSession();

        node0 = seeder.getSink1();
        node1 = seeder.getSink2();

        node30 = seeder.getSameAddNode1();

        node135 = seeder.getNode135();
        node136 = seeder.getNode136();
        node137 = seeder.getNode137();

        node245 = seeder.getNode245();
        node246 = seeder.getNode246();
    }

    //    @Ignore("This is a test in RepoBaseTest which should be run for debugging base class")
    @Test
    public void run_this_test_for_debugging_this_base_class()
    {

    }

    @After
    public void after() throws Exception
    {
    }

    @Configuration
    @Import(RepositoryConfigTest.class)
    @ComponentScan(basePackages = "com.artronics.sdwn.domain")
     static class BaseRepoConfig{

    }

}
