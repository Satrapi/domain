package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.SdwnControllerEntity;
import com.artronics.sdwn.domain.entities.SwitchingNetwork;
import com.artronics.sdwn.domain.helpers.EntityFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class SwitchingNetRepoTest extends BaseRepoTest
{
    private SwitchingNetwork net = EntityFactory.createSwitchingNet(URL);

    @Override
    @Before
    @Transactional
    public void setUp() throws Exception
    {
        super.setUp();

        SdwnControllerEntity ctrl = EntityFactory.createController("foo/url");
        net.setSdwnController(ctrl);
        ctrl.addSwitchingNet(net);

        controllerRepo.save(ctrl);
    }

    @Test
    public void test_findByUrl(){
        SwitchingNetwork persistedNet = switchingNetRepo.findByUrl(URL);

        assertThat(persistedNet.getId(),equalTo(net.getId()));
    }

}