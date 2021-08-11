package com.jwd.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PairDatabase {

    private ArrayList<Pair> dictionaryBase;

    public PairDatabase() {
        this.dictionaryBase = new ArrayList<>();
        fill(this.dictionaryBase);
    }

    private void fill(ArrayList<Pair> base) {
        base.add(new Pair("apple", "яблоко"));
        base.add(new Pair("ball", "мяч"));
        base.add(new Pair( "cinema", "кинотеатр"));
        base.add(new Pair("day", "день"));
        base.add(new Pair("egg", "яйцо"));
        base.add(new Pair("fist", "кулак"));
        base.add(new Pair("game", "игра"));
        base.add(new Pair("house", "дом"));
        base.add(new Pair("ice", "лед"));
        base.add(new Pair("joke", "шутка"));
    }

    public ArrayList<Pair> getDictionaryBase() {
        return dictionaryBase;
    }

}
