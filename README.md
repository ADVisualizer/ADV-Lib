# ADV - Algorithm and Datastructure Visualizer

[![Download](https://api.bintray.com/packages/adv/adv/adv-lib/images/download.svg) ](https://bintray.com/adv/adv/adv-lib/_latestVersion)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e94bc0883d6c43fd8c5741b9d71a007f)](https://app.codacy.com/app/ADV/ADV-Lib?utm_source=github.com&utm_medium=referral&utm_content=ADVisualizer/ADV-Lib&utm_campaign=badger)
[![Build Status](https://travis-ci.org/ADVisualizer/ADV-Lib.svg?branch=develop)](https://travis-ci.org/ADVisualizer/ADV-Lib)
[![codecov](https://codecov.io/gh/ADVisualizer/ADV-Lib/branch/develop/graph/badge.svg)](https://codecov.io/gh/ADVisualizer/ADV-Lib)
<a href="https://structure101.com/"><img src="http://structure101.com/images/s101_170.png" width="90" height="21"></a>



## ADV-Lib
Library Container of the ADV application

ADV can visualize data structures and algorithms you programmed yourself, thereby helping you to understand complex processes.

### Maven
````xml
<dependency>
  <groupId>ch.hsr.adv</groupId>
  <artifactId>adv-lib</artifactId>
  <version>0.3</version>
  <type>pom</type>
</dependency>
````

### Gradle
````groovy
compile 'ch.hsr.adv:adv-lib:0.3'
````
### Usage
Connect to the UI:
````java
ADV.launch(args);
````

Use any of ADV's Modules to implement your data structures and algorithms. Here is an example with the array module:
````java
private static final String[] objectArray = new String[7];
private static final ArrayModule arrayModule = new ArrayModule("ObjectArray", objectArray);
````

Send snapshots of the state of your datastructure to the ADV UI to be displayed:
````java
ADV.snapshot(arrayModule, "Default Initialization of a String array.");
````

Have a look at the [User Codebase Project](https://github.com/ADVisualizer/ADV-User_Codebase) for more examples.
