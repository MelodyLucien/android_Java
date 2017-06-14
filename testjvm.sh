#!/bin/bash

interval=200

pid=`jps | grep Test |awk '{print $1}'`

while [ -z "$pid"  ]
do
 echo "scanning……"
 pid=`jps | grep Test |awk '{print $1}'`
done
	
echo "start monitoring……"
#jstat -gcutil -gccause  $pid $interval
jstat -gccause  $pid $interval
