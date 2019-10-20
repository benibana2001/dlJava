BUILD_DIR = build
BUILD_FRAME_DIR = buildFrame

HOST = http://tk2-255-37178.vs.sakura.ne.jp/portfolio/poll/img/
EXT = jpg
NEWDIR = img
ZEROPAD = 1
MAXPAGE = 100
PREFIX = photo_
STARTPAGE = 0

b:
	javac -d ${BUILD_DIR} -cp src/ src/*.java
	java -classpath ${BUILD_DIR} Main ${HOST} ${EXT} ${NEWDIR} ${ZEROPAD} ${MAXPAGE} ${PREFIX} ${STARTPAGE}

r:
	rm -rf ./${NEWDIR}
	rm ${BUILD_DIR}/*.class

f:
	javac -d ${BUILD_FRAME_DIR} -cp src/ src/DLFrame/*.java
	javac -d ${BUILD_FRAME_DIR} -cp src/ src/TestFrame/*.java
	javac -d ${BUILD_FRAME_DIR} -cp src/ src/DownLoader/WebImageBinder/*.java
	javac -d ${BUILD_FRAME_DIR} -cp src/ src/Util/*.java
	java -cp ${BUILD_FRAME_DIR} TestFrame/TestFrame

jar:
	jar cvf DL.jar -C buildFrame .

jarMakeMani:
	touch DL.mani
	echo "Main-Class: TestFrame.TestFrame" >> DL.mani
	jar uvfm DL.jar DL.mani -C buildFrame .
#	jar cvf DL.jar -C buildFrame .

jarRm:
	rm DL.jar DL.mani

jarExec:
	java -cp DL.jar TestFrame.TestFrame
