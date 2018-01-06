project:
	javac source/Bin.java source/Tree.java source/Pack.java source/Main.java
	echo 'java -classpath "source" Main algo'>algo.exe
	echo 'java -classpath "source" Main stat'>stat.exe
clean:
	rm -f source/*.class
	rm -f algo.exe
	rm -f stat.exe
