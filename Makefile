BUILD_DIR = build
BUILD_FRAME_DIR = buidFrame

HOST = http://tk2-255-37178.vs.sakura.ne.jp/portfolio/poll/img/
EXT = jpg
NEWDIR = img
ZEROPAD = 0
MAXPAGE = 100
PREFIX = photo_

b:
	javac -d ${BUILD_DIR} src/*.java
	java -classpath ${BUILD_DIR} Main ${HOST} ${EXT} ${NEWDIR} ${ZEROPAD} ${MAXPAGE} ${PREFIX}

r:
	rm -rf ./${NEWDIR}
	rm ${BUILD_DIR}/*.class

f:
	javac -d ${BUILD_FRAME_DIR} src/DLFrame/*.java
	javac -cp src/ -d ${BUILD_FRAME_DIR} src/TestFrame/*.java
	java -classpath ${BUILD_FRAME_DIR} TestFrame/TestFrame
