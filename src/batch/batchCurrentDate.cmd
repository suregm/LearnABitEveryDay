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









批处理bat标准化获取当前系统日期的几种方法
操作系统不同，日期格式也可能不同：
星期二 2008-07-29
2008-07-29 星期二
07/29/2008 Tue
Tue 07/29/2008

再考虑到中、英之外的其它语言的操作系统，日期格式的种类实在太多了。
要想标准化地获取当前系统日期2008-07-28，直接截取%date%变量的方法是不行的。
【方案一】BAT + REG
@echo off
rem 无法保证在中、英之外的其它语言的操作系统上得到正确结果
for /f "delims=" %%a in ('reg query "HKEY_CURRENT_USER/Control Panel/International" /v sShortDate') do (
  set "RegDateOld=%%a"
)
set RegDateOld=%RegDateOld:~-8%
reg add "HKEY_CURRENT_USER/Control Panel/International" /v sShortDate /t REG_SZ /d yyyy-M-d /f>nul
set Today=%date: =%
reg add "HKEY_CURRENT_USER/Control Panel/International" /v sShortDate /t REG_SZ /d %RegDateOld% /f>nul
set "Week=Mon Tue Wed Thu Fri Sat Sun 星期一 星期二 星期三 星期四 星期五 星期六 星期日"
for %%a in (%Week%) do (
  call set "Today=%%Today:%%a=%%"
)
echo,%Today%
pause
【方案二】BAT + REG
@echo off
for /f "delims=" %%a in ('reg query "HKEY_CURRENT_USER/Control Panel/International" /v sShortDate') do (
  set "RegDateOld=%%a"
)
set RegDateOld=%RegDateOld:~-8%
reg add "HKEY_CURRENT_USER/Control Panel/International" /v sShortDate /t REG_SZ /d yyyy-M-d /f>nul
set Today=%date: =%
reg add "HKEY_CURRENT_USER/Control Panel/International" /v sShortDate /t REG_SZ /d %RegDateOld% /f>nul
if "%Today:~0,1%" gtr "9" (
  set Today=%Today:~-10%
) else (
  set Today=%Today:~0,10%
)
echo,%Today%
pause
【方案三】BAT + REG
@echo off
for /f "delims=" %%a in ('reg query "HKEY_CURRENT_USER/Control Panel/International" /v sShortDate') do (
  set "RegDateOld=%%a"
)
set RegDateOld=%RegDateOld:~-8%
reg add "HKEY_CURRENT_USER/Control Panel/International" /v sShortDate /t REG_SZ /d yyyy-M-d /f>nul
type nul>"%temp%/MyFile.tmp"
for /f %%a in ('dir "%temp%/MyFile.tmp" ^| findstr /i "MyFile.tmp"') do (
  set Today=%%a
)
reg add "HKEY_CURRENT_USER/Control Panel/International" /v sShortDate /t REG_SZ /d %RegDateOld% /f>nul
echo,%Today%
pause
【方案四】BAT + WMIC
@echo off
for /f "tokens=2 delims==" %%a in ('wmic path win32_operatingsystem get LocalDateTime /value') do (
  set t=%%a
)
set Today=%t:~0,4%-%t:~4,2%-%t:~6,2%
echo,%Today%
pause
【方案五】BAT + VBS
@echo off
>"%temp%/DateCalculate.vbs" echo dt=date()
>>"%temp%/DateCalculate.vbs" echo s=right(year(dt),4) ^& "-" ^& right("0" ^& month(dt),2) ^& "-" ^& right("0" ^& day(dt),2)
>>"%temp%/DateCalculate.vbs" echo wscript.echo s
for /f %%a in ('cscript /nologo "%temp%/DateCalculate.vbs"') do set (
  Today=%%a
)
echo,%Today%
pause
【方案六】BAT + REGEDIT
@echo off
rem 需要保证注册表编辑器没有处于锁定状态
regedit /e "%temp%/bak.reg" "HKEY_CURRENT_USER/Control Panel/International"
>"%temp%/new.reg" echo REGEDIT4
>>"%temp%/new.reg" echo,
>>"%temp%/new.reg" echo [HKEY_CURRENT_USER/Control Panel/International]
>>"%temp%/new.reg" echo "sShortDate"="yyyy-MM-dd"
regedit /s "%temp%/new.reg"
set Today=%date: =%
regedit /s "%temp%/bak.reg"
if "%Today:~0,1%" gtr "9" (
  set Today=%Today:~-10%
) else (
  set Today=%Today:~0,10%
)
echo,%Today%
pause
【方案七】BAT + DEBUG
 @echo off
for /f "tokens=6,8 delims== " %%a in ('^(echo a100^&echo mov ah^,2a^&echo int 21^&echo.^&echo p 2^&echo q^)^|debug^|find "CX"') do (
  set /a y=0x%%a
  set md=%%b
)
set /a m=0x%md:~,2%
set /a d=0x%md:~-2%
set m=0%m%
set d=0%d%
set Today=%y%-%m:~-2%-%d:~-2%
echo,%Today%
pause