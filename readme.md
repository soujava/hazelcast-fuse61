
DISCLAIMER
==========

* This project is a POC for having Hazelcast working together with JBoss Fuse 6.1. 
At this point in time, this project was created to reflect the issue on HZ and provide a working test so the issue can be identified and possible fixed.
[Hazelcast 3.5.3 conflicts with FUSE 6.1](https://github.com/hazelcast/hazelcast/issues/6821)


INSTALL STEPS
==============

Steps to install and test the artifact:

Add URL
```
addurl mvn:com.hazelcast.api/hazelcast-endpoint/1.0.0.RC1-SNAPSHOT/xml/features
```

Install the artifact:
```
features:install hazelcast-endpoint
```

Verify is started:

```
list |grep hazel
[ 431] [Active     ] [            ] [Started] [   60] hazelcast-endpoint (1.0.0.RC1-SNAPSHOT)
```

set your Karaf terminal to read logs:
```log:tail```

Test the HZ endpoint:

Open your FUSE admin panel and login:
```http://localhost:8181/```


Go to the ActiveMQ section, select startEndpoint queue, click on the icon "Send" and paste the contents from the file src/test/resources/mock/weather_ams.json and send the message.


Observe the logs for the several exceptions that are thrown.

```
com.hazelcast.nio.serialization.HazelcastSerializationException: java.lang.IllegalStateException: Bundle is uninstalled
	at com.hazelcast.nio.serialization.SerializationServiceImpl.handleException(SerializationServiceImpl.java:380)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.SerializationServiceImpl.readObject(SerializationServiceImpl.java:331)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.ByteArrayObjectDataInput.readObject(ByteArrayObjectDataInput.java:489)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.ConfigCheck.readData(ConfigCheck.java:215)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.JoinMessage.readData(JoinMessage.java:80)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.JoinRequest.readData(JoinRequest.java:64)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.DataSerializer.read(DataSerializer.java:111)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.DataSerializer.read(DataSerializer.java:39)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.StreamSerializerAdapter.read(StreamSerializerAdapter.java:41)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.SerializationServiceImpl.readObject(SerializationServiceImpl.java:325)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.ByteArrayObjectDataInput.readObject(ByteArrayObjectDataInput.java:489)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.MulticastService.receive(MulticastService.java:155)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.MulticastService.run(MulticastService.java:113)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at java.lang.Thread.run(Thread.java:745)[:1.7.0_91]
Caused by: java.lang.IllegalStateException: Bundle is uninstalled
	at org.apache.felix.framework.Felix.loadBundleClass(Felix.java:1840)[org.apache.felix.framework-4.0.3.redhat-611412.jar:]
	at org.apache.felix.framework.BundleImpl.loadClass(BundleImpl.java:937)[org.apache.felix.framework-4.0.3.redhat-611412.jar:]
	at org.springframework.osgi.util.BundleDelegatingClassLoader.findClass(BundleDelegatingClassLoader.java:99)[147:org.springframework.osgi.core:1.2.1]
	at org.springframework.osgi.util.BundleDelegatingClassLoader.loadClass(BundleDelegatingClassLoader.java:156)[147:org.springframework.osgi.core:1.2.1]
	at java.lang.ClassLoader.loadClass(ClassLoader.java:358)[:1.7.0_91]
	at com.hazelcast.nio.ClassLoaderUtil.tryLoadClass(ClassLoaderUtil.java:125)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.ClassLoaderUtil.loadClass(ClassLoaderUtil.java:98)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.IOUtil$1.resolveClass(IOUtil.java:113)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:1612)[:1.7.0_91]
	at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1517)[:1.7.0_91]
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1771)[:1.7.0_91]
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)[:1.7.0_91]
	at java.io.ObjectInputStream.readObject(ObjectInputStream.java:370)[:1.7.0_91]
	at com.hazelcast.nio.serialization.DefaultSerializers$ObjectSerializer.read(DefaultSerializers.java:201)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.StreamSerializerAdapter.read(StreamSerializerAdapter.java:41)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.SerializationServiceImpl.readObject(SerializationServiceImpl.java:325)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	... 12 more
2015-12-07 17:24:23,232 | WARN  | .MulticastThread | MulticastService                 | ?                                   ? | 427 - com.hazelcast.api.hazelcast-endpoint - 1.0.0.RC1-SNAPSHOT | [172.17.0.1]:5702 [dev] [3.5.3] Received data format is invalid. (An old version of Hazelcast may be running here.)
com.hazelcast.nio.serialization.HazelcastSerializationException: java.lang.IllegalStateException: Bundle is uninstalled
	at com.hazelcast.nio.serialization.SerializationServiceImpl.handleException(SerializationServiceImpl.java:380)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.SerializationServiceImpl.readObject(SerializationServiceImpl.java:331)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.ByteArrayObjectDataInput.readObject(ByteArrayObjectDataInput.java:489)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.ConfigCheck.readData(ConfigCheck.java:215)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.JoinMessage.readData(JoinMessage.java:80)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.JoinRequest.readData(JoinRequest.java:64)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.DataSerializer.read(DataSerializer.java:111)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.DataSerializer.read(DataSerializer.java:39)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.StreamSerializerAdapter.read(StreamSerializerAdapter.java:41)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.SerializationServiceImpl.readObject(SerializationServiceImpl.java:325)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.ByteArrayObjectDataInput.readObject(ByteArrayObjectDataInput.java:489)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.MulticastService.receive(MulticastService.java:155)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.MulticastService.run(MulticastService.java:113)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at java.lang.Thread.run(Thread.java:745)[:1.7.0_91]
Caused by: java.lang.IllegalStateException: Bundle is uninstalled
	at org.apache.felix.framework.Felix.loadBundleClass(Felix.java:1840)[org.apache.felix.framework-4.0.3.redhat-611412.jar:]
	at org.apache.felix.framework.BundleImpl.loadClass(BundleImpl.java:937)[org.apache.felix.framework-4.0.3.redhat-611412.jar:]
	at org.springframework.osgi.util.BundleDelegatingClassLoader.findClass(BundleDelegatingClassLoader.java:99)[147:org.springframework.osgi.core:1.2.1]
	at org.springframework.osgi.util.BundleDelegatingClassLoader.loadClass(BundleDelegatingClassLoader.java:156)[147:org.springframework.osgi.core:1.2.1]
	at java.lang.ClassLoader.loadClass(ClassLoader.java:358)[:1.7.0_91]
	at com.hazelcast.nio.ClassLoaderUtil.tryLoadClass(ClassLoaderUtil.java:125)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.ClassLoaderUtil.loadClass(ClassLoaderUtil.java:98)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.IOUtil$1.resolveClass(IOUtil.java:113)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:1612)[:1.7.0_91]
	at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1517)[:1.7.0_91]
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1771)[:1.7.0_91]
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)[:1.7.0_91]
	at java.io.ObjectInputStream.readObject(ObjectInputStream.java:370)[:1.7.0_91]
	at com.hazelcast.nio.serialization.DefaultSerializers$ObjectSerializer.read(DefaultSerializers.java:201)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.StreamSerializerAdapter.read(StreamSerializerAdapter.java:41)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.SerializationServiceImpl.readObject(SerializationServiceImpl.java:325)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	... 12 more
2015-12-07 17:24:23,232 | WARN  | .MulticastThread | MulticastService                 | ?                                   ? | 426 - com.hazelcast.api.hazelcast-endpoint - 1.0.0.RC1-SNAPSHOT | [172.17.0.1]:5701 [dev] [3.5.3] Received data format is invalid. (An old version of Hazelcast may be running here.)
com.hazelcast.nio.serialization.HazelcastSerializationException: java.lang.IllegalStateException: Bundle is uninstalled
	at com.hazelcast.nio.serialization.SerializationServiceImpl.handleException(SerializationServiceImpl.java:380)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.SerializationServiceImpl.readObject(SerializationServiceImpl.java:331)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.ByteArrayObjectDataInput.readObject(ByteArrayObjectDataInput.java:489)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.ConfigCheck.readData(ConfigCheck.java:215)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.JoinMessage.readData(JoinMessage.java:80)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.DataSerializer.read(DataSerializer.java:111)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.DataSerializer.read(DataSerializer.java:39)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.StreamSerializerAdapter.read(StreamSerializerAdapter.java:41)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.SerializationServiceImpl.readObject(SerializationServiceImpl.java:325)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.ByteArrayObjectDataInput.readObject(ByteArrayObjectDataInput.java:489)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.MulticastService.receive(MulticastService.java:155)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.MulticastService.run(MulticastService.java:113)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at java.lang.Thread.run(Thread.java:745)[:1.7.0_91]
Caused by: java.lang.IllegalStateException: Bundle is uninstalled
	at org.apache.felix.framework.Felix.loadBundleClass(Felix.java:1840)[org.apache.felix.framework-4.0.3.redhat-611412.jar:]
	at org.apache.felix.framework.BundleImpl.loadClass(BundleImpl.java:937)[org.apache.felix.framework-4.0.3.redhat-611412.jar:]
	at org.springframework.osgi.util.BundleDelegatingClassLoader.findClass(BundleDelegatingClassLoader.java:99)[147:org.springframework.osgi.core:1.2.1]
	at org.springframework.osgi.util.BundleDelegatingClassLoader.loadClass(BundleDelegatingClassLoader.java:156)[147:org.springframework.osgi.core:1.2.1]
	at java.lang.ClassLoader.loadClass(ClassLoader.java:358)[:1.7.0_91]
	at com.hazelcast.nio.ClassLoaderUtil.tryLoadClass(ClassLoaderUtil.java:125)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.ClassLoaderUtil.loadClass(ClassLoaderUtil.java:98)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.IOUtil$1.resolveClass(IOUtil.java:113)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:1612)[:1.7.0_91]
	at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1517)[:1.7.0_91]
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1771)[:1.7.0_91]
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)[:1.7.0_91]
	at java.io.ObjectInputStream.readObject(ObjectInputStream.java:370)[:1.7.0_91]
	at com.hazelcast.nio.serialization.DefaultSerializers$ObjectSerializer.read(DefaultSerializers.java:201)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.StreamSerializerAdapter.read(StreamSerializerAdapter.java:41)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.SerializationServiceImpl.readObject(SerializationServiceImpl.java:325)[426:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	... 11 more
2015-12-07 17:24:23,233 | WARN  | .MulticastThread | MulticastService                 | ?                                   ? | 427 - com.hazelcast.api.hazelcast-endpoint - 1.0.0.RC1-SNAPSHOT | [172.17.0.1]:5702 [dev] [3.5.3] Received data format is invalid. (An old version of Hazelcast may be running here.)
com.hazelcast.nio.serialization.HazelcastSerializationException: java.lang.IllegalStateException: Bundle is uninstalled
	at com.hazelcast.nio.serialization.SerializationServiceImpl.handleException(SerializationServiceImpl.java:380)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.SerializationServiceImpl.readObject(SerializationServiceImpl.java:331)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.ByteArrayObjectDataInput.readObject(ByteArrayObjectDataInput.java:489)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.ConfigCheck.readData(ConfigCheck.java:215)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.JoinMessage.readData(JoinMessage.java:80)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.DataSerializer.read(DataSerializer.java:111)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.DataSerializer.read(DataSerializer.java:39)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.StreamSerializerAdapter.read(StreamSerializerAdapter.java:41)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.SerializationServiceImpl.readObject(SerializationServiceImpl.java:325)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.ByteArrayObjectDataInput.readObject(ByteArrayObjectDataInput.java:489)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.MulticastService.receive(MulticastService.java:155)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.cluster.impl.MulticastService.run(MulticastService.java:113)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at java.lang.Thread.run(Thread.java:745)[:1.7.0_91]
Caused by: java.lang.IllegalStateException: Bundle is uninstalled
	at org.apache.felix.framework.Felix.loadBundleClass(Felix.java:1840)[org.apache.felix.framework-4.0.3.redhat-611412.jar:]
	at org.apache.felix.framework.BundleImpl.loadClass(BundleImpl.java:937)[org.apache.felix.framework-4.0.3.redhat-611412.jar:]
	at org.springframework.osgi.util.BundleDelegatingClassLoader.findClass(BundleDelegatingClassLoader.java:99)[147:org.springframework.osgi.core:1.2.1]
	at org.springframework.osgi.util.BundleDelegatingClassLoader.loadClass(BundleDelegatingClassLoader.java:156)[147:org.springframework.osgi.core:1.2.1]
	at java.lang.ClassLoader.loadClass(ClassLoader.java:358)[:1.7.0_91]
	at com.hazelcast.nio.ClassLoaderUtil.tryLoadClass(ClassLoaderUtil.java:125)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.ClassLoaderUtil.loadClass(ClassLoaderUtil.java:98)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.IOUtil$1.resolveClass(IOUtil.java:113)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:1612)[:1.7.0_91]
	at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1517)[:1.7.0_91]
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1771)[:1.7.0_91]
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)[:1.7.0_91]
	at java.io.ObjectInputStream.readObject(ObjectInputStream.java:370)[:1.7.0_91]
	at com.hazelcast.nio.serialization.DefaultSerializers$ObjectSerializer.read(DefaultSerializers.java:201)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.StreamSerializerAdapter.read(StreamSerializerAdapter.java:41)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	at com.hazelcast.nio.serialization.SerializationServiceImpl.readObject(SerializationServiceImpl.java:325)[427:com.hazelcast.api.hazelcast-endpoint:1.0.0.RC1-SNAPSHOT]
	... 11 more
2015-12-07 17:25:45,567 | INFO  | tp1853030668-306 | TransportConnector               | ?                                   ? | 132 - org.apache.activemq.activemq-osgi - 5.9.0.redhat-611412 | Connector vm://amq started
2015-12-07 17:25:45,591 | INFO  | tp1853030668-306 | TransportConnector               | ?                                   ? | 132 - org.apache.activemq.activemq-osgi - 5.9.0.redhat-611412 | Connector vm://amq stopped
^C

JBossFuse:karaf@root> Exception in thread "cached2" java.lang.NoClassDefFoundError: com/hazelcast/cluster/impl/MulticastJoiner$1
	at com.hazelcast.cluster.impl.MulticastJoiner.searchForOtherClusters(MulticastJoiner.java:101)
	at com.hazelcast.cluster.impl.SplitBrainHandler.searchForOtherClusters(SplitBrainHandler.java:48)
	at com.hazelcast.cluster.impl.SplitBrainHandler.run(SplitBrainHandler.java:38)
	at com.hazelcast.util.executor.CachedExecutorServiceDelegate$Worker.run(CachedExecutorServiceDelegate.java:209)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
	at com.hazelcast.util.executor.HazelcastManagedThread.executeRun(HazelcastManagedThread.java:76)
	at com.hazelcast.util.executor.HazelcastManagedThread.run(HazelcastManagedThread.java:92)
Exception in thread "cached4" java.lang.NoClassDefFoundError: com/hazelcast/cluster/MemberInfo
	at com.hazelcast.cluster.impl.ClusterServiceImpl.createMemberInfoList(ClusterServiceImpl.java:1036)
	at com.hazelcast.cluster.impl.ClusterServiceImpl.sendMemberListToOthers(ClusterServiceImpl.java:531)
	at com.hazelcast.cluster.impl.ClusterServiceImpl.access$200(ClusterServiceImpl.java:116)
	at com.hazelcast.cluster.impl.ClusterServiceImpl$3.run(ClusterServiceImpl.java:254)
	at com.hazelcast.util.executor.CachedExecutorServiceDelegate$Worker.run(CachedExecutorServiceDelegate.java:209)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
	at com.hazelcast.util.executor.HazelcastManagedThread.executeRun(HazelcastManagedThread.java:76)
	at com.hazelcast.util.executor.HazelcastManagedThread.run(HazelcastManagedThread.java:92)
Exception in thread "cached1" java.lang.NoClassDefFoundError: com/hazelcast/cluster/impl/MulticastJoiner$1
	at com.hazelcast.cluster.impl.MulticastJoiner.searchForOtherClusters(MulticastJoiner.java:101)
	at com.hazelcast.cluster.impl.SplitBrainHandler.searchForOtherClusters(SplitBrainHandler.java:48)
	at com.hazelcast.cluster.impl.SplitBrainHandler.run(SplitBrainHandler.java:38)
	at com.hazelcast.util.executor.CachedExecutorServiceDelegate$Worker.run(CachedExecutorServiceDelegate.java:209)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
	at com.hazelcast.util.executor.HazelcastManagedThread.executeRun(HazelcastManagedThread.java:76)
	at com.hazelcast.util.executor.HazelcastManagedThread.run(HazelcastManagedThread.java:92)
Exception in thread "cached3" java.lang.NoClassDefFoundError: com/hazelcast/cluster/impl/MulticastJoiner$1
	at com.hazelcast.cluster.impl.MulticastJoiner.searchForOtherClusters(MulticastJoiner.java:101)
	at com.hazelcast.cluster.impl.SplitBrainHandler.searchForOtherClusters(SplitBrainHandler.java:48)
	at com.hazelcast.cluster.impl.SplitBrainHandler.run(SplitBrainHandler.java:38)
	at com.hazelcast.util.executor.CachedExecutorServiceDelegate$Worker.run(CachedExecutorServiceDelegate.java:209)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
	at com.hazelcast.util.executor.HazelcastManagedThread.executeRun(HazelcastManagedThread.java:76)
	at com.hazelcast.util.executor.HazelcastManagedThread.run(HazelcastManagedThread.java:92)
Exception in thread "cached2" java.lang.NoClassDefFoundError: com/hazelcast/cluster/MemberInfo
	at com.hazelcast.cluster.impl.ClusterServiceImpl.createMemberInfoList(ClusterServiceImpl.java:1036)
	at com.hazelcast.cluster.impl.ClusterServiceImpl.sendMemberListToOthers(ClusterServiceImpl.java:531)
	at com.hazelcast.cluster.impl.ClusterServiceImpl.access$200(ClusterServiceImpl.java:116)
	at com.hazelcast.cluster.impl.ClusterServiceImpl$3.run(ClusterServiceImpl.java:254)
	at com.hazelcast.util.executor.CachedExecutorServiceDelegate$Worker.run(CachedExecutorServiceDelegate.java:209)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
	at com.hazelcast.util.executor.HazelcastManagedThread.executeRun(HazelcastManagedThread.java:76)
	at com.hazelcast.util.executor.HazelcastManagedThread.run(HazelcastManagedThread.java:92)
Exception in thread "cached5" java.lang.NoClassDefFoundError: com/hazelcast/cluster/impl/MulticastJoiner$1
	at com.hazelcast.cluster.impl.MulticastJoiner.searchForOtherClusters(MulticastJoiner.java:101)
	at com.hazelcast.cluster.impl.SplitBrainHandler.searchForOtherClusters(SplitBrainHandler.java:48)
	at com.hazelcast.cluster.impl.SplitBrainHandler.run(SplitBrainHandler.java:38)
	at com.hazelcast.util.executor.CachedExecutorServiceDelegate$Worker.run(CachedExecutorServiceDelegate.java:209)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
	at com.hazelcast.util.executor.HazelcastManagedThread.executeRun(HazelcastManagedThread.java:76)
	at com.hazelcast.util.executor.HazelcastManagedThread.run(HazelcastManagedThread.java:92)
Exception in thread "cached3" java.lang.NoClassDefFoundError: com/hazelcast/cluster/impl/MulticastJoiner$1
	at com.hazelcast.cluster.impl.MulticastJoiner.searchForOtherClusters(MulticastJoiner.java:101)
	at com.hazelcast.cluster.impl.SplitBrainHandler.searchForOtherClusters(SplitBrainHandler.java:48)
	at com.hazelcast.cluster.impl.SplitBrainHandler.run(SplitBrainHandler.java:38)
	at com.hazelcast.util.executor.CachedExecutorServiceDelegate$Worker.run(CachedExecutorServiceDelegate.java:209)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
	at com.hazelcast.util.executor.HazelcastManagedThread.executeRun(HazelcastManagedThread.java:76)
	at com.hazelcast.util.executor.HazelcastManagedThread.run(HazelcastManagedThread.java:92)
Exception in thread "cached2" java.lang.NoClassDefFoundError: com/hazelcast/cluster/impl/MulticastJoiner$1
	at com.hazelcast.cluster.impl.MulticastJoiner.searchForOtherClusters(MulticastJoiner.java:101)
	at com.hazelcast.cluster.impl.SplitBrainHandler.searchForOtherClusters(SplitBrainHandler.java:48)
	at com.hazelcast.cluster.impl.SplitBrainHandler.run(SplitBrainHandler.java:38)
	at com.hazelcast.util.executor.CachedExecutorServiceDelegate$Worker.run(CachedExecutorServiceDelegate.java:209)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
	at com.hazelcast.util.executor.HazelcastManagedThread.executeRun(HazelcastManagedThread.java:76)
	at com.hazelcast.util.executor.HazelcastManagedThread.run(HazelcastManagedThread.java:92)
Exception in thread "cached5" java.lang.NoClassDefFoundError: com/hazelcast/cluster/MemberInfo
	at com.hazelcast.cluster.impl.ClusterServiceImpl.createMemberInfoList(ClusterServiceImpl.java:1036)
	at com.hazelcast.cluster.impl.ClusterServiceImpl.sendMemberListToOthers(ClusterServiceImpl.java:531)
	at com.hazelcast.cluster.impl.ClusterServiceImpl.access$200(ClusterServiceImpl.java:116)
	at com.hazelcast.cluster.impl.ClusterServiceImpl$3.run(ClusterServiceImpl.java:254)
	at com.hazelcast.util.executor.CachedExecutorServiceDelegate$Worker.run(CachedExecutorServiceDelegate.java:209)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
	at com.hazelcast.util.executor.HazelcastManagedThread.executeRun(HazelcastManagedThread.java:76)
	at com.hazelcast.util.executor.HazelcastManagedThread.run(HazelcastManagedThread.java:92)
Exception in thread "cached3" java.lang.NoClassDefFoundError: com/hazelcast/cluster/impl/MulticastJoiner$1
	at com.hazelcast.cluster.impl.MulticastJoiner.searchForOtherClusters(MulticastJoiner.java:101)
	at com.hazelcast.cluster.impl.SplitBrainHandler.searchForOtherClusters(SplitBrainHandler.java:48)
	at com.hazelcast.cluster.impl.SplitBrainHandler.run(SplitBrainHandler.java:38)
	at com.hazelcast.util.executor.CachedExecutorServiceDelegate$Worker.run(CachedExecutorServiceDelegate.java:209)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
	at com.hazelcast.util.executor.HazelcastManagedThread.executeRun(HazelcastManagedThread.java:76)
	at com.hazelcast.util.executor.HazelcastManagedThread.run(HazelcastManagedThread.java:92)
```



License
=======

GNU 3
-----

GNU General Public License, version 3.

