#------------------------------------------------------------------------
#            JMP123 Linux Build    http://jmp123.sf.net/
#------------------------------------------------------------------------
echo "Please wait a minute..."
if [ ! -d bin ];then
mkdir bin
fi
#------------------------------------------------------------------------
javac -cp bin -d bin -encoding utf-8 src/jmp123/decoder/*.java
javac -cp bin -d bin -encoding utf-8 src/jmp123/instream/*.java
javac -cp bin -d bin -encoding utf-8 src/jmp123/output/*.java
javac -cp bin -d bin -encoding utf-8 src/jmp123/*.java
javac -cp bin -d bin -encoding utf-8 src/jmp123/test/*.java
javac -cp bin -d bin -encoding utf-8 src/jmp123/gui/album/*.java
javac -cp bin -d bin -encoding utf-8 src/jmp123/gui/*.java
#------------------------------------------------------------------------
if [ ! -d bin/jmp123/gui/resources ];then
mkdir bin/jmp123/gui/resources
fi
cp src/jmp123/gui/resources/*.properties bin/jmp123/gui/resources
#------------------------------------------------------------------------
jar cvfm jmp123.jar manifest.mf -C bin .
#------------------------------------------------------------------------
