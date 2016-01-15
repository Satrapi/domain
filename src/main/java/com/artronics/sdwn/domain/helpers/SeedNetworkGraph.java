package com.artronics.sdwn.domain.helpers;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.entities.SdwnControllerEntity;
import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.repositories.DeviceConnectionRepo;
import com.artronics.sdwn.domain.repositories.NodeRepo;
import com.artronics.sdwn.domain.repositories.SdwnControllerRepo;
import com.artronics.sdwn.domain.repositories.SessionRepo;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


 /*
     Device1
     node number 30 is sameAddNode
     look for code for weight values
     Graph is like
                 sink:0
                   /   \
                 w50  w10
                 /      \
              135 --w20-- 30
                \         /
                w25    w100
                  \    /
                   136
                    |
                   w30
                    |
                   137

     Device2
     node number 30 is sameAddNode
     Graph is like
                 sink
                     |
                    w10
                     |
                    245
                   /   \
                 w20    w100
                /         \
              246 --w30-- 30
  */


@Component
public class SeedNetworkGraph
{
    protected final static Logger log = Logger.getLogger(SeedNetworkGraph.class);
    protected NetworkSession activeSession = new NetworkSession();
    protected NetworkSession expiredSession = new NetworkSession();

    protected SdwnControllerEntity controller = new SdwnControllerEntity("http://controller:port");

    protected DeviceConnectionEntity device1 = new DeviceConnectionEntity("http://device1:port");
    protected DeviceConnectionEntity device2 = new DeviceConnectionEntity("http://device2:port");

    protected SdwnNodeEntity sink1 = new SdwnNodeEntity(0L, device1);
    protected SdwnNodeEntity sink2 = new SdwnNodeEntity(1L, device2);

    protected SdwnNodeEntity sameAddNode1 = new SdwnNodeEntity(30L, device1);
    protected SdwnNodeEntity sameAddNode2 = new SdwnNodeEntity(30L, device2);

    protected SdwnNodeEntity node135 = new SdwnNodeEntity(135L, device1);
    protected SdwnNodeEntity node136 = new SdwnNodeEntity(136L, device1);
    protected SdwnNodeEntity node137 = new SdwnNodeEntity(137L, device1);

    protected SdwnNodeEntity node245 = new SdwnNodeEntity(245L, device2);
    protected SdwnNodeEntity node246 = new SdwnNodeEntity(246L, device2);

    protected SessionRepo sessionRepo;
    protected SdwnControllerRepo controllerRepo;
    protected DeviceConnectionRepo deviceRepo;
    protected NodeRepo nodeRepo;
//    protected NeighborRepo neighborRepo;

    protected boolean persist = false;

    public void seed(boolean persist)
    {
        createNetwork();
        if (persist)
            persistNetwork();
        else
            mockPersistNetwork();

        createNodes(activeSession);
        if (persist) {
            persistNodes();
            persistNodeLinks();
        }else {
            mockPersistNodes();
        }
    }

    protected void createNetwork()
    {
        expiredSession.setStatus(NetworkSession.Status.EXPIRED);

        device1.setSdwnController(controller);
        device1.setSinkNode(sink1);

        device2.setSdwnController(controller);
        device2.setSinkNode(sink2);
    }

    protected void createNodes(NetworkSession session)
    {
        sink1.setSession(session);
        sink2.setSession(session);
        sink1.setType(SdwnNodeEntity.Type.SINK);
        sink2.setType(SdwnNodeEntity.Type.SINK);

        sameAddNode1.setSession(session);
        sameAddNode2.setSession(session);

        node135.setSession(session);
        node136.setSession(session);
        node137.setSession(session);

        node245.setSession(session);
        node246.setSession(session);
    }

    protected void mockPersistNetwork()
    {
        expiredSession.setId(1L);
        activeSession.setId(2L);

        controller.setId(1L);

        device1.setId(1L);
        device2.setId(2L);
    }

    protected void persistNetwork()
    {
        controllerRepo.save(controller);

        sessionRepo.save(expiredSession);
        sessionRepo.save(activeSession);

        deviceRepo.save(device1);
        deviceRepo.save(device2);
    }

    protected void mockPersistNodes()
    {
        sink1.setId(0L);
        sink2.setId(1L);

        sameAddNode1.setId(100L);
        sameAddNode2.setId(200L);

        node135.setId(135L);
        node136.setId(136L);
        node137.setId(137L);

        node245.setId(245L);
        node246.setId(246L);
    }

    protected void persistNodes()
    {
        nodeRepo.save(sink1);
        nodeRepo.save(sink2);

        nodeRepo.save(sameAddNode1);
        nodeRepo.save(sameAddNode2);

        nodeRepo.save(node135);
        nodeRepo.save(node136);
        nodeRepo.save(node137);

        nodeRepo.save(node245);
        nodeRepo.save(node246);
    }

    @Ignore
    @Transactional
    protected void persistNodeLinks()
    {
        // node246
        SdwnNeighbor n246_245 = new SdwnNeighbor(node245, 20D, 235);
        SdwnNeighbor n246_30 = new SdwnNeighbor(sameAddNode2, 30D, 225);

//        node246.addNeighbor(n246_245);
//        node246.addNeighbor(n246_30);
        nodeRepo.persist(node246);
//        neighborRepo.persist(n246_245);
//        neighborRepo.persist(n246_30);
    }

    @Autowired
    @Qualifier("networkSession")
    public void setActiveSession(NetworkSession activeSession)
    {
        this.activeSession = activeSession;
    }

    @Autowired
    public void setSessionRepo(SessionRepo sessionRepo)
    {
        this.sessionRepo = sessionRepo;
    }

    @Autowired
    public void setNodeRepo(NodeRepo nodeRepo)
    {
        this.nodeRepo = nodeRepo;
    }

    @Autowired
    public void setSdwnControllerRepo(
            SdwnControllerRepo controllerRepo)
    {
        this.controllerRepo = controllerRepo;
    }

    @Autowired
    public void setDeviceRepo(DeviceConnectionRepo deviceRepo)
    {
        this.deviceRepo = deviceRepo;
    }

//    @Autowired
//    public void setNeighborRepo(NeighborRepo neighborRepo)
//    {
//        this.neighborRepo = neighborRepo;
//    }

    public NetworkSession getActiveSession()
    {
        return activeSession;
    }

    public SdwnNodeEntity getSink1()
    {
        return sink1;
    }

    public SdwnNodeEntity getSink2()
    {
        return sink2;
    }

    public DeviceConnectionEntity getDevice1()
    {
        return device1;
    }

    public DeviceConnectionEntity getDevice2()
    {
        return device2;
    }

    public SdwnNodeEntity getNode246()
    {
        return node246;
    }

    public SdwnNodeEntity getNode245()
    {
        return node245;
    }

    public SdwnNodeEntity getNode137()
    {
        return node137;
    }

    public SdwnNodeEntity getNode136()
    {
        return node136;
    }

    public SdwnNodeEntity getNode135()
    {
        return node135;
    }

    public SdwnNodeEntity getSameAddNode2()
    {
        return sameAddNode2;
    }

    public SdwnNodeEntity getSameAddNode1()
    {
        return sameAddNode1;
    }
}
