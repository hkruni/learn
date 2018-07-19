package learn.zookeeper.curator;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

public class MyCuratorWatcher implements CuratorWatcher {

	@Override
	public void process(WatchedEvent event) throws Exception {

		System.out.println("触发watcher，节点路径为：" + event.getPath());
		System.out.println("触发watcher，事件类型为：" + event.getType());
		System.out.println("触发watcher，节点状态为：" + event.getState());

		//触发watcher，节点路径为：/create/one
		//触发watcher，事件类型为：NodeDeleted
		//触发watcher，节点状态为：SyncConnected
	}

}
