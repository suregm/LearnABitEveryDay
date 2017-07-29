@echo off

echo %0
echo.
echo.

if not exist %1 (
    echo example:
    :: %0 %1
    echo %0 fileName
    echo fileName: reffering to the fileName to be renamed.
    echo.
    echo.
    goto end
)

:: %~x1 表示文件%1的扩展名，“~x”表示取变量的扩展名
set extension=%~x1
:: "tokens"变量个数，"delims"列举分隔符
for /F "tokens=1-3 delims=/- " %%A in ('date/T') do set date=%%A%%B%%C
ren %1 %date%%extension%
echo.
echo the file %1 has been renamed as %date%%extension% successfully.
set extension=
set date=
:end