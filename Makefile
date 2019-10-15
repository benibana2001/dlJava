BUILD_DIR = build

HOST =http://tk2-255-37178.vs.sakura.ne.jp/portfolio/poll/img/
EXT = jpg
NEWDIR = imgTest
ZEROPAD = 0
MAXPAGE = 100
PREFIX = photo_

b:
	javac -d ${BUILD_DIR} src/*.java
	java -classpath ${BUILD_DIR} Main ${HOST} ${EXT} ${NEWDIR} ${ZEROPAD} ${MAXPAGE} ${PREFIX}

r:
	rm -rf ./${NEWDIR}
	rm ${BUILD_DIR}/*.class
