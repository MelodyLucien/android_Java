set path=E:\jdk1.5.0\bin;%path%

set currpath=.\
if "%OS%" == "Windows_NT" set currpath=%~dp0%

set src=%currpath%src
set dest=%currpath%classes
set classpath=%dest%

copy %src%\*.properties %dest%
copy %src%\uselocal\*.properties %dest%\uselocal

javac   -sourcepath %src%  -d %dest% %src%\*.java

javac   -sourcepath %src%  -d %dest% %src%\uselocal\*.java
