@echo off

echo %0
echo.
echo.

rem after locking the PC, users can only log into the system with the right password.
echo being locking the PC...
:: 调用“user32.dll”动态库中的“LockWorkStation”函数
rundll32.exe user32.dll,LockWorkStation
