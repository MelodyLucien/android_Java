set path=E:\jdk1.5.0\bin;%path%

set currpath=.\
if "%OS%" == "Windows_NT" set currpath=%~dp0%

set src=%currpath%src
set dest=%currpath%classes
set classpath=%dest%

copy %src%\*.jpg  %dest%
copy %src%\*.htm  %dest%
copy %src%\*.wav  %dest%
copy %src%\*.mid  %dest%

javac   -sourcepath %src%  -d %dest% %src%\*.java

