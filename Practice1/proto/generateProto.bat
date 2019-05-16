@title generate message
@cd %~dp0
@rem %~dp0 “d”为Drive的缩写，即为驱动器，磁盘、“p”为Path缩写，即为路径，目录
@rem current dir: %~dp0
%~d0

@echo batch generate proto 
@rem @author chencan
@rem @echo off常用于开头，表示不显示所有的命令行信息，包括此句,相当于在每一行开头加 @
@rem 在批处理文件里面用 for，变量就用 %%
@rem set是对参数的定义，动态的话需要加上/p的条件

@echo off
set /p protoFile=please input filename:
if exist "%protoFile%" goto:process 
rem call跳转到子批处理，并且不终止父批处理，start 是在新窗口执行
echo file not exist!
rem 再次执行当前批处理
call %0


:process
@echo file:"%protoFile%" exist!
@echo generating message,please wait...
@protoc.exe --java_out=../src %protoFile%
@echo generate success!
pause
goto:eof

