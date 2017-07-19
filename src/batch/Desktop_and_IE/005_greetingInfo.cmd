@echo off

echo %0
echo.
echo.

echo 正在个性化问候信息...
echo Windows Registry Editor Version 5.00 >temp.reg
echo [HKEY_CURRENT_USER\Control Panel\International] >>temp.reg
:: 第一个“t”表示是否显示上下午时间，后面的每个“t”代表两个字节，用于指定用户显示信息的长度
echo "sTimeFormat"="HH点mm分ttttt" >>temp.reg
echo.
echo 08:00到17:59在系统托盘区显示“努力工作”
:: 设定键值项s1159，指定00:00-11:59时段显示信息
:: 此时会显示“07点54分努力工作”
echo "s1159"="努力工作" >>temp.reg
echo.
echo 18:00到07:59在系统托盘区显示“GOOD NIGHT”
:: 设定键值项s2359，指定12:00-23:59时段显示信息
echo "s2359"="GOOD NIGHT" >>temp.reg
regedit /s temp.reg
del /q /f temp.reg >nul
echo.
echo 成功定制任务栏！