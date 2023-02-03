package test;

import Parser.Parser;

public class TestParser {

    public static void main(String[] args) {
        Parser test = new Parser("Test.txt");
        test.scanFile();

    }
    
}
