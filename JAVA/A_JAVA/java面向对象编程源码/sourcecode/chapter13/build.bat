set path=E:\jdk1.5.0\bin;%path%

set currpath=.\
if "%OS%" == "Windows_NT" set currpath=%~dp0%

set src=%currpath%src
set dest=%currpath%classes
set classpath=%dest%

javac   -sourcepath %src%  -d %dest% %src%\allrun\*.java
javac   -sourcepath %src%  -d %dest% %src%\commu\*.java
javac   -sourcepath %src%  -d %dest% %src%\control\*.java
javac   -sourcepath %src%  -d %dest% %src%\correctstart\*.java
javac   -sourcepath %src%  -d %dest% %src%\countbirds\*.java
javac   -sourcepath %src%  -d %dest% %src%\deadlock\*.java
javac   -sourcepath %src%  -d %dest% %src%\exhandler\*.java
javac   -sourcepath %src%  -d %dest% %src%\extendth\*.java
javac   -sourcepath %src%  -d %dest% %src%\group\*.java
javac   -sourcepath %src%  -d %dest% %src%\interrupt\*.java
javac   -sourcepath %src%  -d %dest% %src%\join\*.java
javac   -sourcepath %src%  -d %dest% %src%\mypack\*.java
javac   -sourcepath %src%  -d %dest% %src%\occupycpu\*.java
javac   -sourcepath %src%  -d %dest% %src%\problem\*.java
javac   -sourcepath %src%  -d %dest% %src%\releaselock\*.java
javac   -sourcepath %src%  -d %dest% %src%\runimpl\*.java
javac   -sourcepath %src%  -d %dest% %src%\sharevar\*.java
javac   -sourcepath %src%  -d %dest% %src%\suspend\*.java
javac   -sourcepath %src%  -d %dest% %src%\syn\*.java
javac   -sourcepath %src%  -d %dest% %src%\synsleep\*.java
javac   -sourcepath %src%  -d %dest% %src%\synstatic\*.java
javac   -sourcepath %src%  -d %dest% %src%\threadref\*.java
javac   -sourcepath %src%  -d %dest% %src%\twoversion\*.java
javac   -sourcepath %src%  -d %dest% %src%\usetimer\*.java
javac   -sourcepath %src%  -d %dest% %src%\waitio\*.java
javac   -sourcepath %src%  -d %dest% %src%\withdaemon\*.java
javac   -sourcepath %src%  -d %dest% %src%\withex\*.java
javac   -sourcepath %src%  -d %dest% %src%\wrongstart\*.java
javac   -sourcepath %src%  -d %dest% %src%\*.java
