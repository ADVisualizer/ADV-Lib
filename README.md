# ADV - Algorithm and Datastructure Visualizer

[![Download](https://api.bintray.com/packages/adv/adv/adv-lib/images/download.svg) ](https://bintray.com/adv/adv/adv-lib/_latestVersion)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e94bc0883d6c43fd8c5741b9d71a007f)](https://app.codacy.com/app/ADV/ADV-Lib?utm_source=github.com&utm_medium=referral&utm_content=ADVisualizer/ADV-Lib&utm_campaign=badger)
[![Build Status](https://travis-ci.org/ADVisualizer/ADV-Lib.svg?branch=develop)](https://travis-ci.org/ADVisualizer/ADV-Lib)
[![codecov](https://codecov.io/gh/ADVisualizer/ADV-Lib/branch/develop/graph/badge.svg)](https://codecov.io/gh/ADVisualizer/ADV-Lib)
<a href="https://structure101.com/"><img src="http://structure101.com/images/s101_170.png" width="90" height="21"></a>

The Algorithm & Data Structure Visualizer (ADV) helps students to understand the concepts of several data structures and algorithms, taught at the University of Applied Science in Rapperswil (HSR).

## ADV-Lib
The ADV-Lib is a Java Library offering classes and interfaces to visualize data structures. Once implemented, the state of a data structure can be sent to the [ADV-UI](https://github.com/ADVisualizer/ADV-UI) to be displayed. Algorithms can be visualized by the use of styles.

### Install
The ADV-Lib is available on jCenter. It requires Java 11 or higher.

#### Gradle
````groovy
compile 'ch.hsr.adv:adv-lib:2.0'
````

#### Maven
````xml
<dependency>
  <groupId>ch.hsr.adv</groupId>
  <artifactId>adv-lib</artifactId>
  <version>2.0</version>
</dependency>
````

#### Starter Projects
ADV offeres Starter Projects for both Maven and Gradle. These Projects are empty IntelliJ Projects with pre-configered ADV dependencies.
[Gradle Starter](https://github.com/ADVisualizer/ADV-Starter-Gradle)
[Maven Starter](https://github.com/ADVisualizer/ADV-Starter-Maven)

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

Send snapshots of the state of your data structure to the ADV UI to be displayed:
````java
ADV.snapshot(arrayModule, "Default Initialization of a String array.");
````

Have a look at the [User Codebase Project](https://github.com/ADVisualizer/ADV-User_Codebase) for more examples.
