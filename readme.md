
DISCLAIMER
==========

* This project is a POC for having Hazelcast working together with JBoss Fuse 6.1. 
At this point in time, this project was created to reflect the issue on HZ and provide a working test so the issue can be identified and possible fixed.
[Hazelcast 3.5.3 conflicts with FUSE 6.1](https://github.com/hazelcast/hazelcast/issues/6821)


REQUIRED SOFTWARE
=================
Download and install JBOSS FUSE 6.1

[jboss-fuse-6.1.0.GA](http://www.jboss.org/download-manager/file/jboss-fuse-6.1.0.GA-medium_zip.zip)


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

```
log:tail
```

Test the HZ endpoint:

Open your FUSE admin panel and login:
```
http://localhost:8181/
```


Go to the ActiveMQ section, select startEndpoint queue, click on the icon "Send" and paste the contents from the file src/test/resources/mock/weather_ams.json and send the message.


Observe the logs for the warn (An old version of Hazelcast may be running here.)

```
2015-12-07 17:24:23,233 | WARN  | .MulticastThread | MulticastService                 | ?                                   ? | 427 - com.hazelcast.api.hazelcast-endpoint - 1.0.0.RC1-SNAPSHOT | [172.17.0.1]:5702 [dev] [3.5.3] Received data format is invalid. (An old version of Hazelcast may be running here.)
```



License
=======

GNU 3
-----

GNU General Public License, version 3.

