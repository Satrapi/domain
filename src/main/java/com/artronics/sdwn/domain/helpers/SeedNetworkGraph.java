package com.artronics.sdwn.domain.helpers;

import com.artronics.sdwn.domain.entities.DeviceConnectionEntity;
import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.entities.SdwnControllerEntity;
import com.artronics.sdwn.domain.entities.node.SdwnNeighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import com.artronics.sdwn.domain.repositories.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SeedNetworkGraph
{
    private final static Logger log = Logger.getLogger(SeedNetworkGraph.class);
    private NetworkSession activeSession = new NetworkSession();
    private NetworkSession expiredSession = new NetworkSession();

    private SdwnControllerEntity controller = new SdwnControllerEntity("http://controller:port");

    private DeviceConnectionEntity device1 = new DeviceConnectionEntity("http://device1:port");
    private DeviceConnectionEntity device2 = new DeviceConnectionEntity("http://device2:port");

    private SdwnNodeEntity sink1 = new SdwnNodeEntity(0L, device1);
    private SdwnNodeEntity sink2 = new SdwnNodeEntity(1L, device2);

    private SdwnNodeEntity sameAddNode1 = new SdwnNodeEntity(30L, device1);
    private SdwnNodeEntity sameAddNode2 = new SdwnNodeEntity(30L, device2);

    private SdwnNodeEntity node135 = new SdwnNodeEntity(135L, device1);
    private SdwnNodeEntity node136 = new SdwnNodeEntity(136L, device1);
    private SdwnNodeEntity node137 = new SdwnNodeEntity(137L, device1);

    private SdwnNodeEntity node245 = new SdwnNodeEntity(245L, device2);
    private SdwnNodeEntity node246 = new SdwnNodeEntity(246L, device2);

    private SessionRepo sessionRepo;
    private SdwnControllerRepo controllerRepo;
    private DeviceConnectionRepo deviceRepo;
    private NodeRepo nodeRepo;
    private NeighborRepo neighborRepo;

    private boolean persist = false;

    public void seed(boolean persist)
    {
        createNetwork();
        persistNetwork(persist);

        if (persist) {
            createNodes(expiredSession);
//            persistNodes(persist);
            sessionRepo.save(expiredSession);
        }
        createNodes(activeSession);

        persistNodes(persist);
        persistNodeLinks();
    }

    private void createNetwork()
    {
        expiredSession.setStatus(NetworkSession.Status.EXPIRED);

        device1.setSdwnController(controller);
        device1.setSinkAddress(0L);

        device2.setSdwnController(controller);
        device2.setSinkAddress(1L);
    }

    private void createNodes(NetworkSession session)
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

    private void persistNetwork(boolean persist)
    {
        if (persist) {
            controllerRepo.save(controller);

            sessionRepo.save(expiredSession);
            sessionRepo.save(activeSession);

            deviceRepo.save(device1);
            deviceRepo.save(device2);
        }else {
            expiredSession.setId(1L);
            activeSession.setId(2L);

            controller.setId(1L);

            device1.setId(1L);
            device2.setId(2L);
        }
    }

    private void persistNodes(boolean persist)
    {
        if (persist) {
            nodeRepo.save(sink1);
            nodeRepo.save(sink2);

            nodeRepo.save(sameAddNode1);
            nodeRepo.save(sameAddNode2);

            nodeRepo.save(node135);
            nodeRepo.save(node136);
            nodeRepo.save(node137);

            nodeRepo.save(node245);
            nodeRepo.save(node246);
        }else {
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
    }

    @Transactional
    private void persistNodeLinks(){
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
                           |     |
                          w30
                          /
                        137

         */



        /*
            Device2
            node number 30 is sameAddNode
            look for code for weight values
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

        SdwnNeighbor n1_245 = new SdwnNeighbor(sink2,node245,10D,245);
//        sink2.getNeighbors().add(n1_245);
//        neighborRepo.save(n1_245);
//        nodeRepo.save(sink2);

        SdwnNeighbor n245_246 = new SdwnNeighbor(node245,node246,20D,235);
//        neighborRepo.persist(n245_246,node245);
        SdwnNeighbor n245_30 = new SdwnNeighbor(node245,sameAddNode2,100D,155);
//        neighborRepo.persist(n245_30,node245);
//        node245.getNeighbors().add(n245_30);
//        node245.getNeighbors().add(n245_246);

//        nodeRepo.save(node245);

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

    @Autowired
    public void setNeighborRepo(NeighborRepo neighborRepo)
    {
        this.neighborRepo = neighborRepo;
    }

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
