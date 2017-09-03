set path=E:\jdk1.5.0\bin;%path%

set currpath=.\
if "%OS%" == "Windows_NT" set currpath=%~dp0%

set src=%currpath%src
set dest=%currpath%classes
set javadoc_dir=%currpath%doc\api
set classpath=%dest%

copy %src%\Manifest.txt %dest%

javac -sourcepath %src%  -d %dest% %src%\com\abc\dollapp\doll\Doll.java

javac -sourcepath %src%  -d %dest% %src%\com\abc\dollapp\doll\extend\SmartDoll.java

javac -sourcepath %src%  -d %dest% %src%\com\abc\dollapp\main\AppMain.java

javadoc -author  -version  -sourcepath %src% -d %javadoc_dir% com.abc.dollapp.doll  com.abc.dollapp.doll.extend com.abc.dollapp.main
