@echo off
echo create files by BATCH.

echo.
echo.
echo being creating file_0.txt ~ file_99.txt totally 100 files...

for /l %%n in (0, 1, 99) do (
    echo file %%n >>file_%%n.txt
)

echo.
echo.
echo created complete.

echo.
echo.
pause