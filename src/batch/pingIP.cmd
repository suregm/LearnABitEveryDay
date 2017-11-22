@echo off

echo %0
echo.
echo.

　　date /t > IPList.txt
　　time /t >> IPList.txt
　　echo =========== >> IPList.txt
　　for /L %%G in (200,1,254) Do (
        for /f "delims="  %%a  in ('Ping -n 1 192.168.1.%%G^|findstr /i "TTL"')  do (
            echo,%%a >>IPList.txt
        )
    )
exit