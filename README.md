dujar
=====

> My first ever Kotlin thingie, yay! 🥳

So this is basically `du` for `.jar` files - it enables you to peak at a jar file
and see the sizes of the classes in the file.

It is still early days and I'd like it to get it to work something like [goweight](https://github.com/jondot/goweight) with the JSON output and all. 

Also needs more and actual command-line arguments and maybe even a kotlin-native build.

## Why?

I needed something like this at work and also needed a good reason to learn Kotlin.

**Example output**

Ran it on a build of [zefaker](https://github.com/creditdatamw/zefaker), oof - those YAMLs takin up space huh.

```
$ java -jar dujar.jar zefaker-all.jar

1.4 MiB en-US.yml
1.0 MiB org/codehaus/groovy/runtime/ArrayUtil.class
937.8 KiB       groovyjarjarantlr4/v4/unicode/UnicodeData.class
526.3 KiB       org/apache/poi/sl/draw/geom/presetShapeDefinitions.xml
507.2 KiB       org/apache/poi/xssf/usermodel/presetTableStyles.xml 
504.7 KiB       org/apache/groovy/util/Maps.class
356.2 KiB       nl.yml
```

## Building

Assuming you have the Kotlin compiler on your PATH

```sh
$ kotlinc dujar.kt -include-runtime -d dujar.jar
```

## Running

```sh
$ java -jar dujar.jar /path/to/file.jar
```

---

Copyright (c) Zikani Nyirenda Mwase
