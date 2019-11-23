package br.edu.utfpr.tsi.sd.core.controller;

public class NoopControl implements Controls {
    @Override
    public boolean forward() {
        return false;
    }

    @Override
    public boolean left() {
        return false;
    }

    @Override
    public boolean right() {
        return false;
    }

    @Override
    public boolean shoot() {
        return false;
    }
}
