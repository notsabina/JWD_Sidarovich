package com.jwd.service;

import com.jwd.entity.Pair;
import com.jwd.exception.EmptyWordException;
import com.jwd.exception.NullPairException;

public interface EnRuDictionary {
    void addPair(Pair pair) throws EmptyWordException, NullPairException;
    int getDictionarySize();
    void printWords();
    void findPair(String word, boolean english) throws EmptyWordException;
    String getRandomWord();
    double takeTry(String english, String russian) throws EmptyWordException;
}
