package main;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import parser.MINIGrammar;
import testsuite.MINIException;


public class MINIProgramTester {
	@Test public void testValidPrograms() {
		File validFolder = new File("res/example_code/valid");
		File[] sourceFiles = validFolder.listFiles();
		for (File f: sourceFiles) {
			if (f.isFile() && f.getName().endsWith(".m")) {
				try {
					MINIGrammar.parse(f);
				} catch (MINIException e) {
					// TEST FAILURE
					assertFalse("Failed to parse file " + f.getAbsolutePath(), true);
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test public void testInvalidPrograms() {
		File validFolder = new File("res/example_code/invalid");
		File[] sourceFiles = validFolder.listFiles();
		for (File f: sourceFiles) {
			if (f.isFile() && f.getName().endsWith(".m")) {
				try {
					MINIGrammar.parse(f);
					assertFalse("Successfully parsed file that should contain errors " + f.getAbsolutePath(), true);
				} catch (MINIException e) {
					// TEST SUCCESS
					e.printStackTrace();
				}
			}
		}
	}
}
