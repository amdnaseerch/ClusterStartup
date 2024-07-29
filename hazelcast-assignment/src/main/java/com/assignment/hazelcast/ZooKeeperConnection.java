package com.assignment.hazelcast;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZooKeeperConnection {

	private static final String ZOOKEEPER_ADDRESS = "localhost:2181";
	private ZooKeeper zooKeeper;
	private CountDownLatch countDownLat = new CountDownLatch(1);

	public void connect() throws IOException, InterruptedException {
		zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, 10000, event -> {
			if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
				countDownLat.countDown();
			}
		});
		countDownLat.await();
	}

	public ZooKeeper getZooKeeper() {
		return zooKeeper;
	}

	public void keepAlive() throws InterruptedException {
		while (true) {
			Thread.sleep(1000);
		}
	}

	public void close() throws InterruptedException {
		if (zooKeeper != null) {
			zooKeeper.close();
		}
	}
}
