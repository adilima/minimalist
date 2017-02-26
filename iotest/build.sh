#!/system/bin/sh -e
#
# create R.java
aapt package -v -f \
             -M ./AndroidManifest.xml \
             -I $PREFIX/share/aapt/android.jar \
             -J src \
             -S res \
             -m


# compile the java sources
# THIS EXAMPLE USING ecj, and we should find out which version
# If using jack then we must do like this:
#   jack --classpath $ANDROID_HOME/platforms/android-n/android.jar \
#        --import [path/to/import/lib/*.jar \
#        --output-dex bin/ \
#        src/ gen/
# And then, no more using dx to produce classes.dex

#####################################################################
#
#ecj -d ./obj -classpath $HOME/../usr/share/java/android.jar \
#	     -sourcepath ./src $(find src -type f -name "*.java")
#
#dx --dex --verbose --output=./bin/classes.dex ./obj

jack --classpath $PREFIX/share/java/android.jar \
	--output-dex bin/ \
	src/ gen/

# make the apk

aapt package -v -f \
             -M ./AndroidManifest.xml \
             -S ./res \
             -F bin/IOTest.apk


# add the classes.dex to the apk
cd bin
aapt add -f IOTest.apk classes.dex

echo "sign the apk"
apksigner -p eveline ../iotest-debug.key IOTest.apk ../IOTest.apk

cd ..
echo "and make it accessible to the outside world"
chmod 777 IOTest.apk

mv -v IOTest.apk $HOME/downloads/
echo "Our IOTest.apk is ready to go"
echo

