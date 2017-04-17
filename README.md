
[![Build Status](https://travis-ci.org/TungstenX/MultiDownloader.svg?branch=master)](https://travis-ci.org/TungstenX/MultiDownloader) 

^ It builds; still figuring out how Travis CI deals with own dependencies

Find me on [stackoverflow](http://stackoverflow.com/users/537566/tungstenx)

# MultiDownloader
A Java client that uses the MultiDownloaderLib library

## Usage
From the commandline:
```
java -jar MultiDownloader.jar <--url=url> [--parts=numberOfThreads] [--help] [--buffersize=size]
```
The options:

| Option | Mandatory | Description |
| ------ | :-------: | ------------|
| --url=url | X | The URL of the file to download |
| --parts=number | | The default is 4. Number of threads to run |
| --buffersize=size | | The default is 8192. Reading buffer size when concatenating the file parts |

*I'm following the Spring convention ([more here](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html)), i.e. two dashes (--) then the parameter/argument name, then equals sign (=) and then the value (No spaces).  The parameter/argument is treated as a flag if the equals sign and value are omitted.*
*The file will be downloaded into the current directory*

## To build
You'll need Gemeenskaplik version 0.0.6, also available as one of my projects.

### Powered by
This README.md was made using VIM

[![Andr&#233; Labuschagn&#233;](http://gravatar.com/avatar/88ebc726d33c8ddba2534d1d6f93e638?s=144)](https://www.ParanoidAndroid.co.za) |
---|
[Andr&#233; Labuschagn&#233;](https://www.ParanoidAndroid.co.za) |

