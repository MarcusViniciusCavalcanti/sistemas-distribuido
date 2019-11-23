package br.edu.utfpr.tsi.sd.core.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Setter
@Getter
@Accessors(fluent = true)
public class RemoteControl implements Controls {

    private boolean forward;
    private boolean left;
    private boolean right;
    private boolean shoot;

}
