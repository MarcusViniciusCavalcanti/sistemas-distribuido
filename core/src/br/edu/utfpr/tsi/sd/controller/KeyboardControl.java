package br.edu.utfpr.tsi.sd.controller;

import static com.badlogic.gdx.Gdx.input;
import static com.badlogic.gdx.Input.Keys.*;

public class KeyboardControl implements Controls {

    @Override
    public boolean forward() {
        return input.isKeyPressed(UP);
    }

    @Override
    public boolean left() {
        return input.isKeyPressed(LEFT);
    }

    @Override
    public boolean right() {
        return input.isKeyPressed(RIGHT);
    }

    @Override
    public boolean shoot() {
        return input.isKeyPressed(SPACE);
    }
}
