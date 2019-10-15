BUILD = build
IMG_DIR = img

b:
	javac -d ${BUILD} src/*.java
	java -classpath BUILD Main https:// png img 0 40 x

r:
	rm -rf ./${IMG_DIR}
	rm ${BUILD}/*.class
