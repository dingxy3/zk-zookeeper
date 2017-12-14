import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Created by Administrator on 2017/12/14.
 */
public class zookp {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
       ZooKeeper zoo = new ZooKeeper("127.0.0.1:2181",30000, new TestWatcher() );
       String node = "/node2" ;
       Stat stat =zoo.exists(node,false);
       if (null == stat){
           String creatResult = zoo.create(node,"test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
       }
       byte[] b = zoo.getData(node,false,stat);
       System.out.println(new String(b));
       zoo.close();;

    }
}
    class TestWatcher implements Watcher{

       @Override
       public void process(WatchedEvent watchedEvent) {
           System.out.println("---------------------------------");
           System.out.println("path :"+watchedEvent.getPath());
           System.out.println("state:"+watchedEvent.getState());
           System.out.println("type:"+watchedEvent.getType());
           System.out.println("---------------------------------");

       }
}
