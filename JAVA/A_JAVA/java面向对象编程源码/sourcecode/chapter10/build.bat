set path=E:\jdk1.5.0\bin;%path%

set currpath=.\
if "%OS%" == "Windows_NT" set currpath=%~dp0%

set src=%currpath%src
set dest=%currpath%classes
set classpath=%dest%


javac   -sourcepath %src%  -d %dest% %src%\init\*.java
javac   -sourcepath %src%  -d %dest% %src%\initbase\*.java
javac   -sourcepath %src%  -d %dest% %src%\loadtester\*.java
javac   -sourcepath %src%  -d %dest% %src%\*.java
