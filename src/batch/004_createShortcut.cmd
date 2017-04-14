@echo off

echo %0
echo.
echo.

::G:\Code\LearnABitEveryDay!\src\batch>004_createShortcut.cmd 003_fullPath.cmd
if "%1"=="" (
    echo the correct syntax is:
    echo %0 filename
    echo parameter 'filename' means the name of the referred file.
    echo.
    echo the batch cannot create shortcut, for not referring to file.
    goto end
)

echo being creating shortcut......
set shortcutName=%~n1.url
echo [InternetShortcut] > %shortcutName%
echo URL=%~f1 >>%shortcutName%
echo IconIndex=1 >>%shortcutName%
echo IconFile=%windir%\system32\shell32.dll >>%shortcutName%
copy %shortcutName% "%userprofile%\desktop" >nul
echo.
echo the shortcut of the file %1 %shortcutName% has been created successfully.
set shortcutName=

:end
echo.
echo.
pause