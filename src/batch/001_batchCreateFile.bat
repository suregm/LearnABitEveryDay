@echo off
echo being creating file_0.txt ~ file_99.txt totally 100 files...
echo.
echo.
for /l %%n in (0, 1, 99) do (
    echo file %%n >>file_%%n.txt
)
echo created complete.