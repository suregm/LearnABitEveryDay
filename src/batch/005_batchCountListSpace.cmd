@echo off

echo %0
echo.
echo.

echo being counting space, wait a minute...
echo --------------------------------------
for /f "tokens=*" %%a in ('dir') do (
    echo "%%a"|find " files">nul && for /f "tokens=3*" %%b in ("%%a") do (
        echo the current folder's space is: %%b%%c
    )
)
echo --------------------------------------

echo the children folder's spaces are:
for /f %%a in ('dir/ad/s/b') do (
    for /f "tokens=*" %%b in ('"dir %%a\"') do (
        echo "%%b"|find " files">nul && for /f "tokens=3*" %%c in ("%%b") do (
            echo %%a: %%c%%d
        )
    )
)