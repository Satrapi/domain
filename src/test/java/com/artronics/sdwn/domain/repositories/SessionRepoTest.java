package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.entities.NetworkSession;
import org.junit.Test;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class SessionRepoTest extends BaseRepoTest
{
    @Test
    @Transactional
    public void test_expire(){
        NetworkSession s = new NetworkSession();
        sessionRepo.save(s);

        sessionRepo.expire();

        NetworkSession actSession = sessionRepo.findOne(s.getId());

        assertThat(actSession.getStatus(),equalTo(NetworkSession.Status.EXPIRED));
    }

    @Test(expected = IncorrectResultSizeDataAccessException.class)
    public void it_should_throw_exp_if_there_are_more_than_one_ACTIVE_sessions(){
        NetworkSession s = new NetworkSession();
        sessionRepo.save(s);

        NetworkSession s1 = new NetworkSession();
        sessionRepo.save(s1);

        sessionRepo.expire();
    }

}