package com.jwd.entity;

public class Pair {
    private final String english;
    private final String russian;

    public Pair(final String english, final String russian) {
        this.english = english;
        this.russian = russian;
    }

    public String getEnglish() {
        return english;
    }

    public String getRussian() {
        return russian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;

        return getEnglish() != null ? getEnglish().equals(pair.getEnglish()) : pair.getEnglish() == null;
    }

    @Override
    public int hashCode() {
        return getEnglish() != null ? getEnglish().hashCode() : 0;
    }

    @Override
    public String toString() {
        return english +
               " - " + russian;
    }
}
