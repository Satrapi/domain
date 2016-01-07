package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.SdwnControllerEntity;
import com.artronics.sdwn.domain.helpers.EntityFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RepositoryConfigTest.class,
//        SatrapiApplication.class
})
@ActiveProfiles("test")
public class BaseRepoTest
{
    protected final static String URL = "http://foo.com:8080";

    @Autowired
    protected SdwnControllerRepo controllerRepo;

    @Autowired
    protected SwitchingNetRepo switchingNetRepo;

    @Before
    @Transactional
    public void setUp() throws Exception
    {
    }

    //    @Ignore("This is a test in RepoBaseTest which should be run for debugging base class")
    @Test
    public void run_this_test_for_debugging_this_base_class()
    {

    }

    @After
    public void after() throws Exception
    {
        controllerRepo.deleteAll();
    }

    public SdwnControllerEntity persistCtrl(String url){
        return controllerRepo.save(EntityFactory.createController(url));
    }

}
