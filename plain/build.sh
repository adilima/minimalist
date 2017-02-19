
# create R.java
aapt package -v -f \
             -M ./AndroidManifest.xml 
\
             -I $PREFIX/../home/android.jar \
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


ecj -d ./obj -classpath $PREFIX/../home/android.jar \
             -sourcepath ./src



dx --dex --verbose --output=./bin/classes.dex ./obj



# make the apk

aapt package -v -f \
             -M ./AndroidManifest.xml \
             -S ./res \
             -F ./bin/MiniTest.apk



# add the classes.dex to the apk

aapt add -f MiniTest.apk bin/classes.dex

