package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.SdwnControllerEntity;
import com.artronics.sdwn.domain.helpers.EntityFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class DeviceConnetionRepo extends BaseRepoTest
{
    private DeviceConnectionEntity net = EntityFactory.createSwitchingNet(URL);
    private SdwnControllerEntity controller = EntityFactory.createController("someUrl");

    @Override
    @Before
    @Transactional
    public void setUp() throws Exception
    {
        super.setUp();

        SdwnControllerEntity ctrl = EntityFactory.createController("foo/url");
        net.setSdwnController(ctrl);
//        ctrl.addSwitchingNet(net);

        controllerRepo.save(ctrl);
    }

    @Test
    @Transactional
    public void it_should_be_pers_with_controller(){
        SdwnControllerEntity ctrl = EntityFactory.createController("someFoo");
        ctrl=controllerRepo.save(ctrl);

        DeviceConnectionEntity net = EntityFactory.createSwitchingNet("someOtherFoo");
        net.setSdwnController(ctrl);
        deviceConnectionRepo.save(net);
    }

    @Test
    public void test_findByUrl(){
        DeviceConnectionEntity persistedNet = deviceConnectionRepo.findByUrl(URL);

        assertThat(persistedNet.getId(),equalTo(net.getId()));
    }

    @Test
    @Transactional
    public void save_a_device(){
        DeviceConnectionEntity net = EntityFactory.createSwitchingNet("foo");
        controllerRepo.save(controller);
        net.setSdwnController(controller);

        deviceConnectionRepo.create(net, controller.getId());
        assertNotNull(net.getId());
    }

    @Test
    public void it_should_update_net_if_it_is_already_persisted(){
        SdwnControllerEntity ctrl = EntityFactory.createController("someFooU");
        persistCtrl(ctrl);
        DeviceConnectionEntity net=persistNet(ctrl, "urlForNet");

        SdwnControllerEntity newCtrl = EntityFactory.createController("newUrl");
        persistCtrl(newCtrl);

        deviceConnectionRepo.create(net, newCtrl.getId());

        DeviceConnectionEntity updatedNet = deviceConnectionRepo.findByUrl("urlForNet");

        assertThat(updatedNet.getSdwnController().getUrl(),equalTo("newUrl"));
    }

    protected SdwnControllerEntity persistCtrl(SdwnControllerEntity ctrl){
        controllerRepo.save(ctrl);

        return ctrl;
    }

    protected DeviceConnectionEntity persistNet(SdwnControllerEntity ctrl, String netUrl){
        DeviceConnectionEntity net= deviceConnectionRepo.create(EntityFactory.createSwitchingNet(netUrl), ctrl.getId());

        return net;
    }
}