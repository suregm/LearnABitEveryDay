@echo off

echo %0
echo.
echo.

echo 正在定制任务栏...
echo Windows Registry Editor Version 5.00 >temp.reg
:: 限制键值项所处子键的位置
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer] >>temp.reg
rem 是否自动隐藏不活动图标
echo "EnableAutoTray"=dword:00000001 >>temp.reg
echo {HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\TrayNotify] >>temp.reg
echo "IconStreams"=- >>temp.reg
rem "PastIconStreams"=- ，表示清除“不活动图标”的历史记录
echo "PastIconsStream"=- >>temp.reg
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\Advanced] >>temp.reg
rem 任务栏分组显示
echo "TaskbarGlomming"=dword:00000001 >>temp.reg
rem 相同程序个数>=7时，显示在一组中
echo "TaskbarGroupSize"=dword:00000007 >>temp.reg
rem 气泡提示信息
echo "EnableBalloonTips"=dword:00000000 >>temp.reg
:: 限制键值项所处子键的位置
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Policies\Explorer] >>temp.reg
echo "NoToolbarsOnTaskbar"=dword:00000001 >>temp.reg
rem 是否禁止任务栏中的系统托盘折叠显示
echo "NoAutoTrayNotify"=dword:00000001 >>temp.reg
echo "HideClock"=dword:000000001 >>temp.reg
echo "LockTaskbar"=dword:00000001 >>temp.reg

regedit /s temp.reg
del /q /f temp.reg >nul
echo.
echo 成功定制任务栏！