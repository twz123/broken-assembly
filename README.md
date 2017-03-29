Broken JAR file produced by Maven Assembly Plugin
=================================================

This is a small sample project that demonstrates an issue with JAR files
created by the [Maven Assembly Plugin][1]. They are somehow broken under
certain circumstances, i.e. some directory entries are erroneously archived as
file entries, which makes those files unextractable via the `jar x` command.

[1]: https://maven.apache.org/components/plugins/maven-assembly-plugin/


## Verify the problem

Run `./mvnw clean verify`.

The test `com.github.twz123.brokenassembly.BrokenAssemblyIT.unjarBrokenAssembly`
should fail with an exception like the following:

    java.io.FileNotFoundException: META-INF/maven (Is a directory)
            at java.io.FileOutputStream.open0(Native Method)
            at java.io.FileOutputStream.open(FileOutputStream.java:270)
            at java.io.FileOutputStream.<init>(FileOutputStream.java:213)
            at java.io.FileOutputStream.<init>(FileOutputStream.java:162)
            at sun.tools.jar.Main.copy(Main.java:909)
            at sun.tools.jar.Main.extractFile(Main.java:1069)
            at sun.tools.jar.Main.extract(Main.java:981)
            at sun.tools.jar.Main.run(Main.java:311)
            at sun.tools.jar.Main.main(Main.java:1288)

Tested on Linux x64 using Oracle Java 1.8.0\_121 (build 1.8.0\_121-b13).
