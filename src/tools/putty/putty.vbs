---Just Do It---------------------------------
让VBS脚本自动在词本中输入一行文字"Hello, welcome to cfan".
Dim WshShell
Set WshShell=WScript.CreateObject("WScript.Shell")
WshShell.Run "notepad"
WScript.Sleep 200
WshShell.AppActivate " 无标题 - 记事本 "
WshShell.SendKeys "hello, welcome to cfan"
我们最常用的记事本没有Word、WPS那样的自动定时存盘功能,其实利用VBS脚本再加上SendKeys命令,就能弥补这个遗憾.打开记事本,输入以下内容(以容易描述和分析,把代码分为四个部分):
'第一部分：定义变量和对象
Dim WshShell, AutoSaveTime, TXTFileName
AutoSaveTime=300000
Set WshShell=WScript.CreateObject("WScript.Shell")
TXTFileName=InputBox("请输入你要创建的文件名(不能用中文和纯数字)：")
'第二部分：打开并激活记事本
WshShell.Run "notepad"
WScript.Sleep 200
WshShell.AppActivate "无标题 - 记事本"
'第三部分：用输入的文件名存盘
WshShell.SendKeys "^s"
WScript.Sleep 300
WshShell.SendKeys TXTFileName
WScript.Sleep 300
WshShell.SendKeys "%s"
WScript.Sleep AutoSaveTime
'第四部分：自动定时存盘
While WshShell.AppActivate (TXTFileName)=True
WshShell.SendKeys "^s"
WScript.Sleep AutoSaveTime
Wend
WScript.Quit
将其保存为记事本.vbs,以后要使用记事本时,都通过双击这个脚本文件来打开.
程序翻译
这个脚本的基本思路是定时向记事本发送Ctrl+S这个存盘组合键.
第一部分:定义了脚本中需要用到的变量和对象."AutoSaveTime"变量用来设置自动存盘间隔,单位为毫秒,这里设置为5分钟."TXTFileName"变量通过输入框取得你要创建的文本文件名称.
第二部分:运行记事本,对于Windows本身提供的程序,比如计算器等,可直接在"WshShell.Run"后输入程序名称,如"calc"对于非系统程序,则可输入完全路径,但要注意使用8.3格式输入,比如" "D:\Progra~1\Tencent\QQ.exe" "
第三部分:这里用SendKeys命令执行了这样的操作流程(请注意每个操作之间延时命令的使用):
在记事本中按Ctrl+S组合键→弹出保存文件的窗口→输入文件名→按Alt+S组合键进行保存(默认保存在"我的文档"目录).
第四部分:定时存盘的关键,通过"While......Wend"这个当条件为"真"时循环命令,实现自动存盘代码" WshShell.SendKeys "^s" "和定时代码" WScript.Sleep AutoSaveTime "的重复执行.因为不能让这个定时存盘循环一直执行,退出记事本后,必须自动退出脚本并结束循环,所以设计了一个循环判断条件" WshShell.AppActivate TXTFileName=True ",当记事本运行中时,可以激活记事本窗口,这个条件运行结果为"True",定时存盘循环一直执行,退出记事本后,脚本无法激活记事本窗口,就会路出循环,执行"Wend"后面的"WScript.Quit"退出脚本.
VBS中Sendkey键盘对应的码表

Key Code
------------------------------
Shift +
Ctrl ^
Alt %
BACKSPACE {BACKSPACE}, {BS}, or {BKSP}
BREAK {BREAK}
CAPS LOCK {CAPSLOCK}
DEL or DELETE {DELETE} or {DEL}
DOWN ARROW {DOWN}
END {END}
ENTER {ENTER}or ~
ESC {ESC}
HELP {HELP}
HOME {HOME}
INS or INSERT {INSERT} or {INS}
LEFT ARROW {LEFT}
NUM LOCK {NUMLOCK}
PAGE DOWN {PGDN}
PAGE UP {PGUP}
PRINT SCREEN {PRTSC}
RIGHT ARROW {RIGHT}
SCROLL LOCK {SCROLLLOCK}
TAB {TAB}
UP ARROW {UP}
F1 {F1}
F2 {F2}
F3 {F3}
F4 {F4}
F5 {F5}
F6 {F6}
F7 {F7}
F8 {F8}
F9 {F9}
F10 {F10}
F11 {F11}
F12 {F12}
F13 {F13}
F14 {F14}
F15 {F15}
F16 {F16}