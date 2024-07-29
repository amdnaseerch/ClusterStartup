package com.assignment.hazelcast;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class NodeCoordinator {

	private static final String LOCK_NODE = "/startupLock";
	private ZooKeeperConnection zooKeeperConnection;

	public NodeCoordinator(ZooKeeperConnection zooKeeperConnection) {
		this.zooKeeperConnection = zooKeeperConnection;
	}

	public void coordinateStartup() throws KeeperException, InterruptedException {
		ZooKeeper keeper = zooKeeperConnection.getZooKeeper();
		Stat stat = keeper.exists(LOCK_NODE, false);

		if (stat == null) {
			// Node not existing, creating a persistent node.
			try {
				keeper.create(LOCK_NODE, "initialized".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				System.out.println("We are started!");
			} catch (KeeperException e) {
				//No action	
			}
		}
	}
}
