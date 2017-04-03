package com.github.twz123.brokenassembly;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class BrokenAssemblyIT {

    @Rule
    public final TemporaryFolder tmp = new TemporaryFolder();

    @Test
    public void unjarBrokenAssembly() throws Exception {
        final String[] command = { //
                pathToJarBinary(), "xf", System.getenv("BROKEN_ASSEMBLY_JAR") //
        };

        System.out.println("Executing " + Arrays.toString(command));

        final ProcessBuilder unjarTemplate = new ProcessBuilder() //
                .inheritIO() //
                .directory(tmp.getRoot()) //
                .command(command);

        final Process unjar = unjarTemplate.start();

        assertThat("Non-zero exit code from jar executable.", unjar.waitFor(), is(0));
    }

    private String pathToJarBinary() {
        final String javaHome = System.getenv("JAVA_HOME");
        return javaHome == null ? "jar" : Paths.get(javaHome, "bin", "jar").toString();
    }
}
