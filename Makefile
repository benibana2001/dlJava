BUILD = build

b:
	javac -d BUILD src/*.java
	java -classpath BUILD Main

r:
	rm ./*.png
	rm BUILD/*.class
