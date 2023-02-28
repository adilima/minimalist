
SOURCES = src/com/example/testing/MiniTest.java \
    src/com/example/testing/R.java 

ANDROID_JAR = $(USERPROFILE)/android/share/android.jar
DX_JAR = $(USERPROFILE)\android\tools\bin\dx-1.6.jar 
JARSIGNER = $(USERPROFILE)\vs\jdk-11.0.11\bin\jarsigner.exe
KEYTOOL = $(USERPROFILE)\vs\jdk-11.0.11\bin\keytool.exe

all: $(SOURCES)
	@ecj -d obj -sourcepath ./src -1.6 -cp $(ANDROID_JAR) $(SOURCES)
	@java -jar $(DX_JAR) --dex --output=classes.dex obj/
	@aapt package -v -f -M AndroidManifest.xml -I $(ANDROID_JAR) -S res -A assets -F MiniTest.apk
	@aapt add -f MiniTest.apk classes.dex 
	

src/com/example/testing/R.java: 
	aapt package -v -f -M AndroidManifest.xml -I $(ANDROID_JAR) -J src -S res -m

sign-apk: minitest.keystore MiniTest.apk
	$(JARSIGNER) -sigalg SHA1withRSA -digestalg SHA1 -keystore minitest.keystore MiniTest.apk minitest

minitest.keystore:
	$(KEYTOOL) -genkey -v -keystore minitest.keystore -alias minitest -keyalg RSA -keysize 2048 -validity 10000

