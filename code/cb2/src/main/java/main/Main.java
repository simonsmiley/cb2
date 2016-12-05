package main;

import java.io.File;

import components.FileNode;
import middleware.NameTable;
import parser.MINIGrammar;
import testsuite.MINIException;
import visitors.NameAndTypeChecker;
import visitors.PrettyPrinter;

public class Main {

    public static void main(String... args) {
        NameTable globalNameTable = new NameTable(null);
        FileNode classes = null;
        try {
            File sourceFile = new File("res" + File.separator + "example_code" + File.separator + "valid"
                    + File.separator + "pretty_much_everything.m");
            classes = MINIGrammar.parse(sourceFile);
            PrettyPrinter prettyPrinter = new PrettyPrinter();
            classes.accept(prettyPrinter, null);
            System.out.println(prettyPrinter.toString());
            NameAndTypeChecker checker = new NameAndTypeChecker();
            classes.accept(checker, globalNameTable);
            System.out.println(globalNameTable);
        } catch (MINIException e) {
            e.printStackTrace();
        }
        System.out.println(globalNameTable);

    }
}