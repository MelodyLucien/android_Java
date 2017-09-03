#!/bin/bash
TOP=`pwd`

function croot(){
  cd ${TOP}
}



function capp(){
   if [ -d ./app ];then
      cd app/src/main/java
      tree -L 4
   else
     echo "sorry ,you are not in root dir of App "
   fi
}




