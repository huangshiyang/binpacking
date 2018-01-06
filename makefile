project:
	javac source/Bin.java source/Tree.java source/Pack.java source/Main.java
	echo 'java -classpath "source" Main exemples/exemple100.txt'>algo.exe
	echo 'java -classpath "source" Main exemples/exemple500.txt'>>algo.exe
	echo 'java -classpath "source" Main exemples/exemple1000.txt'>>algo.exe
	echo 'java -classpath "source" Main exemples/monexemple.txt'>>algo.exe
clean:
	rm -f source/*.class
	rm -f algo.exe
