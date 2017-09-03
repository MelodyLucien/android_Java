set path=E:\jdk1.5.0\bin;%path%

set currpath=.\
if "%OS%" == "Windows_NT" set currpath=%~dp0%

set src=%currpath%src
set dest=%currpath%classes
set classpath=%dest%

javac   -sourcepath %src%  -d %dest% %src%\hidestatic\*.java
javac   -sourcepath %src%  -d %dest% %src%\poly\*.java
javac   -sourcepath %src%  -d %dest% %src%\privatetest\*.java
javac   -sourcepath %src%  -d %dest% %src%\usecooper\*.java
javac   -sourcepath %src%  -d %dest% %src%\usesuper\*.java
javac   -sourcepath %src% -Xlint:unchecked  -d %dest% %src%\*.java
