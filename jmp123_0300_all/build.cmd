@echo ит╨Р...
@rem ----------------------------------------------------------------
@rem              JMP123 Build    http://jmp123.sf.net/
@rem ----------------------------------------------------------------
if not exist bin md bin
set classpath=bin
@rem ----------------------------------------------------------------
javac -d bin -encoding utf-8 src\jmp123\decoder\*.java
javac -d bin -encoding utf-8 src\jmp123\instream\*.java
javac -d bin -encoding utf-8 src\jmp123\output\*.java
javac -d bin -encoding utf-8 src\jmp123\*.java
javac -d bin -encoding utf-8 src\jmp123\test\*.java
javac -d bin -encoding utf-8 src\jmp123\gui\album\*.java
javac -d bin -encoding utf-8 src\jmp123\gui\*.java
@rem ----------------------------------------------------------------
xcopy src\jmp123\gui\resources\*.* bin\jmp123\gui\resources\ /s
@rem ----------------------------------------------------------------
jar cvfm jmp123.jar manifest.mf -C bin .
@rem ----------------------------------------------------------------
@pause
