set path=E:\jdk1.5.0\bin;%path%

set currpath=.\
if "%OS%" == "Windows_NT" set currpath=%~dp0%

set src=%currpath%src
set dest=%currpath%classes
set classpath=%dest%


javac -Xlint:unchecked  -sourcepath %src%  -d %dest% %src%\diffnames\*.java
javac -Xlint:unchecked  -sourcepath %src%  -d %dest% %src%\inherit\*.java
javac -Xlint:unchecked  -sourcepath %src%  -d %dest% %src%\innerinterface\*.java
javac -Xlint:unchecked  -sourcepath %src%  -d %dest% %src%\noname\*.java
javac -Xlint:unchecked  -sourcepath %src%  -d %dest% %src%\nooverride\*.java
javac -Xlint:unchecked  -sourcepath %src%  -d %dest% %src%\outerref\*.java
javac -Xlint:unchecked  -sourcepath %src%  -d %dest% %src%\visitcontrol\*.java
javac -Xlint:unchecked  -sourcepath %src%  -d %dest% %src%\visitstatic\*.java

