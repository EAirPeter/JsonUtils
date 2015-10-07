# JsonUtils
---
Some JSON utilities written with Java.  
This project is licensed under [GPLv3](http://www.gnu.org/licenses/gpl.html).  
This project follows [ECMA-404](http://www.ecma-international.org/publications/files/ECMA-ST/ECMA-404.pdf).  

## Features
1. Translate JSON string or file to JSON values.
2. Translate some Java Objects to JSON values.
3. Unwrap JSON values to Java Objects.
4. Format a JSON string or file.
5. Validate a JSON string or file.

## Build
This project requires JDK7 to build.

* First, clone this project to local.
* Run ```gradlew build``` and get the artifacts in ```build/libs```.
* Alternatively, Run ```gradlew install``` to build the project and install the artifacts into local maven repository.
* The artifacts consist of binary jar(```JsonUtils-VER.jar```) and source jar(```JsonUtils-VER-src.jar```).

## JSON values
Any JSON value ```v``` can be represented by a ```JsonBase```.  
If ```v``` is ```null```, it's a JSON ```null``` value.  
If ```v``` is an instance of ```JsonObject```, it's a JSON object.  
If ```v``` is an instance of ```JsonArray```, it's a JSON array.  
If ```v``` is an instance of ```JsonString```, it's a JSON string.  
If ```v``` is an instance of ```JsonNumber```, it's a JSON number.  
If ```v``` is an instance of ```JsonBool```, it's a JSON ```true``` value when ```v.data``` is ```true```.  
If ```v``` is an instance of ```JsonBool```, it's a JSON ```false``` value when ```v.data``` is ```false```.  

## Detailed usage
Refer to javadoc.