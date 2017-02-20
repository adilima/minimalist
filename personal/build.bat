@echo off

set ANDROID_HOME=%USERPROFILE%\AppData\Local\Android\Sdk
set PATH=%PATH%;%ANDROID_HOME%\build-tools\25.0.2;%ANDROID_HOME%\tools\bin;%JAVA_HOME%\bin 

aapt.exe package -v -f -M AndroidManifest.xml -I %ANDROID_HOME%\platforms\android-23\android.jar -J src -S res -m 

jack.jar --classpath %ANDROID_HOME%\platforms\android-23\android.jar --output-dex bin src gen 

echo "Packing APK"
aapt.exe package -v -f -M AndroidManifest.xml -I %ANDROID_HOME%\platforms\android-23\android.jar -S res -A assets -F bin\Personal.apk

echo "Merge the dex"
cd bin
aapt.exe add Personal.apk classes.dex

cd ..
echo "Done."
