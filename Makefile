BUILD = build
IMG_DIR = img

b:
	javac -d ${BUILD} src/*.java
	java -classpath BUILD Main

r:
	rm -rf ./${IMG_DIR}
	rm ${BUILD}/*.class
