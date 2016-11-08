package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import parser.MINIGrammar;
import testsuite.MINIException;

@RunWith(Parameterized.class)
public class InvalidMiniProgramTester {
    private File file;

    public InvalidMiniProgramTester(File file, String filename) {
        this.file = file;
    }

    @Test
    public void testFile() {
        try {
            MINIGrammar.parse(file);
            assertFalse("Successfully parsed file that should contain errors " + file.getAbsolutePath(), true);
        } catch (MINIException e) {
            // TEST SUCCESS
            if (System.getenv("DEBUG") != null) {
                System.out.println(e.toString());
            }
            String errorLine = file.getName().substring(0, 3);
            try {
                int lineNumber = Integer.parseInt(errorLine);
                assertEquals("The error occurred in an unexpected line", lineNumber, e.getLineNumber());
            } catch (NumberFormatException ne) {}
        }

    }

    @Parameters(name="{1}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        File validFolder = new File("res" + File.separator + "example_code" + File.separator + "invalid");
        File[] sourceFiles = validFolder.listFiles();
        for (File f : sourceFiles) {
            if (f.isFile() && f.getName().endsWith(".m")) {
                data.add(new Object[] { f , f.getName()});
            }
        }
        return data;
    }
}
