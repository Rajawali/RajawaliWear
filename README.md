![Rajawali](http://www.rozengain.com/files/rajawali-logo.jpg)

#RajawaliWear

Rajawali Wear is a plugin for the Rajawali 3D engine. This plugin aims to add drop in support for Android Wear device.

#First Run

You need a local build of the latest Rajawali Framework. Currently Rajawali is not available on maven so a local build must be created. To build a local release of Rajawali simply perform a checkout of the library then run the gradle tasks ```clean assembleRelease uploadArchives```.

## Linux
```
git clone https://github.com/MasDennis/Rajawali.git
./Rajawali/gradlew clean assembleRelease uploadArchives
```

## Windows
```
git clone https://github.com/MasDennis/Rajawali.git
Rajawali/gradlew.bat clean assembleRelease uploadArchives
```

Once complete you can simply open the project in AS and deploy the example application to your wear device.

#Wear Example

Running the wear example create a scene similar to the below clip.

![WearDemo](https://cloud.githubusercontent.com/assets/1614281/6610492/64aad72a-c836-11e4-9548-30f0978e65ca.gif)
