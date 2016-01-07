package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.SdwnControllerEntity;
import com.artronics.sdwn.domain.entities.SwitchingNetwork;
import com.artronics.sdwn.domain.helpers.EntityFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class SdwnControllerRepoTest extends BaseRepoTest
{
    private SdwnControllerEntity controller= EntityFactory.createController(URL);

    @Override
    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        controllerRepo.save(controller);
    }

    @Test
    public void test_findByUrl(){
        SdwnControllerEntity persistedCtrl = controllerRepo.findByUrl(URL);

        assertThat(persistedCtrl.getId(),equalTo(controller.getId()));
    }

    @Test
    @Transactional
    public void it_should_add_a_SwitchinNetwork(){
        SwitchingNetwork net = EntityFactory.createSwitchingNet("someUrl");
        controllerRepo.addSwitchingNet(controller,net);

        assertNotNull(net.getId());
        assertNotNull(net.getSdwnController());

        SdwnControllerEntity con =controllerRepo.findOne(controller.getId());
        assertThat(con.getSwitchingNetworks().size(),equalTo(1));
    }


}