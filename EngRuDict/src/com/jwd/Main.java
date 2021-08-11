package com.jwd;

import com.jwd.console.DictionaryConsoleApplication;
import com.jwd.service.EnRuDictionary;
import com.jwd.service.impl.EnRuDictionaryImpl;

import java.util.Dictionary;

public class Main {

    public static void main(String[] args) {
        EnRuDictionary dict = new EnRuDictionaryImpl();
        DictionaryConsoleApplication dictionaryConsoleApplication = new DictionaryConsoleApplication(dict);
        dictionaryConsoleApplication.start();
    }
}
