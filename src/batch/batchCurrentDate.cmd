@echo off

echo %date%
:: ％date:~a,b% 获得当前时间字符串中的某一部分，其中a和b都可以是负值，a是所要取部分在当前时间字符串中的位置（0表示第一个字符），a为负值表示倒数，b为所取字符串长度
echo %date:~5,5%

echo %time%
:: 如果小时只有一位数字，造首位空格问题，使用如下方法补0
set hh=%time:~0,2%
if /i %hh% LSS 10 (
    set hh=0%time:~1,1%
)
echo %hh%

set date1=%DATE:~0,4%%DATE:~5,2%%DATE:~8,2%
set time1=%TIME:~0,2%%TIME:~3,2%%TIME:~6,2%
echo Date1=%date1%
echo Time1=%time1%

rem echo 批处理获取当前系统日期时间

rem echo 获取当前日期字符串
for /f "tokens=1,2,3 delims=/- " %%a in ("%date%") do @set D=%%a%%b%%c
rem echo 获取当前时间字符串
for /f "tokens=1,2,3 delims=:." %%a in ("%time%") do @set T=%%a%%b%%c
rem echo 如当前小时小于10，将空格替换为0
set T=%T: =0%
rem echo 显示输出日期时间字符串
echo %D%
echo %T%

for /f "tokens=1,2,3 delims=/- " %%a in ('date /t') do @set bb=%%a-%%b-%%c
echo %bb%
