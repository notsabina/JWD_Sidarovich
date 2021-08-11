package com.jwd.service.impl;

import com.jwd.entity.Pair;
import com.jwd.entity.PairDatabase;
import com.jwd.exception.EmptyWordException;
import com.jwd.exception.NullPairException;
import com.jwd.service.EnRuDictionary;
import com.jwd.validator.DictionaryValidator;

import java.util.Random;

public class EnRuDictionaryImpl implements EnRuDictionary {

    private PairDatabase database;
    private final DictionaryValidator validator = new DictionaryValidator();

    public EnRuDictionaryImpl() {
        this.database = new PairDatabase();
    }

    @Override
    public void printWords(){
        for (Pair pair : this.database.getDictionaryBase()) {
            System.out.println(pair);
        }
    }

    @Override
    public void findPair(String word, boolean english) throws EmptyWordException {
        validator.validateWordIsNull(word);
        boolean found = false;
        if (english) {
            for (Pair pair : this.database.getDictionaryBase()) {
                found = checkEn(pair, word);
                if (found) {
                    break;
                }
            }
        } else {
            for (Pair pair : this.database.getDictionaryBase()) {
                found = checkRu(pair, word);
                if (found) {
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("No such word in the dictionary in the chosen language!");
        }
    }

    @Override
    public void addPair(final Pair pair) throws EmptyWordException, NullPairException {
        validator.validatePairIsNull(pair);
        this.database.getDictionaryBase().removeIf(el -> (el.equals(pair)));
        this.database.getDictionaryBase().add(pair);
    }

    @Override
    public int getDictionarySize() {
        return this.database.getDictionaryBase().size();
    }

    private boolean checkEn(Pair pair, String word) {
        if (pair.getEnglish().equals(word)) {
            System.out.println(pair);
        }
        return pair.getEnglish().equals(word);
    }

    private boolean checkRu(Pair pair, String word) {
        if (pair.getRussian().equals(word)) {
            System.out.println(pair);
        }
        return pair.getRussian().equals(word);
    }

    public String getRandomWord() {
        Random random = new Random();
        return database.getDictionaryBase().get(random.nextInt(getDictionarySize())).getEnglish();
    }

    public double takeTry(String english, String russian) throws EmptyWordException {
        validator.validateWordIsNull(english);
        validator.validateWordIsNull(russian);
        double result = 0;
        for (Pair pair : database.getDictionaryBase()) {
            if (pair.getRussian().equals(russian) && pair.getEnglish().equals(english)) {
                result = 1;
                break;
            }
        }
        if (result == 0) {
            System.out.println("WRONG! Correct answer is:" );
            findPair(english, true);
        }
       return result;
    }
}
