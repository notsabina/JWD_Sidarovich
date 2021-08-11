package com.jwd.validator;

import com.jwd.entity.Pair;
import com.jwd.exception.EmptyWordException;
import com.jwd.exception.NullPairException;

public class DictionaryValidator {
    public void validateWordIsNull(String parameter) throws EmptyWordException {
        if (parameter == null) {
            throw new EmptyWordException("Exception: Word == null.");
        }
    }

    public void validatePairIsNull(Pair parameter) throws EmptyWordException, NullPairException {
        if (parameter == null) {
            throw new NullPairException("Exception: Pair == null.");
        } else if (parameter.getRussian() == null || parameter.getEnglish() == null) {
            throw new EmptyWordException("Exception: one of the words is null");
        }
    }
}
