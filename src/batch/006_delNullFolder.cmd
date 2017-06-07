@echo off

echo %0
echo.
echo.

echo deleting the empty folder...
echo ----------------------------
cd. >listnull.txt
for /f "delims=" %%i in ('dir /ad /b /s') do (
    dir /b "%%i"|findstr .>nul || echo %%i >>listnull.txt
)
set /a sum=0
for /f %%i in (listnull.txt) do (
    echo deleted success: %%i
    rd /q %%i
    set /a sum=sum+1
)
echo ----------------------------
echo deleted %sum% empty folders successfully in total.
echo.
set sum=
del /q listnull.txt >nul