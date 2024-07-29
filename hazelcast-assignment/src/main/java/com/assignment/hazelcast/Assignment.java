package com.assignment.hazelcast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Assignment {

    private static final Logger LOG = LoggerFactory.getLogger(Assignment.class);

    public static void main(String[] args) {
        ZooKeeperConnection zookeeperConnection = new ZooKeeperConnection();
        NodeCoordinator coordinator = new NodeCoordinator(zookeeperConnection);
        try {
            zookeeperConnection.connect();
            coordinator.coordinateStartup();
            // Custom method to keep the application running.
            zookeeperConnection.keepAlive();
        } catch (Exception e) {
            LOG.error("Exception occurred: ", e);
        } finally {
            try {
                zookeeperConnection.close();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
