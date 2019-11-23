package br.edu.utfpr.tsi.sd.core.tools;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Randomize {

    public static <T> T fromList(List<T> things) {
        return things.stream()
                .skip((int) (things.size() * Math.random()))
                .findAny()
                .get();
    }
}
