package model.characthers;

import javax.validation.constraints.NotNull;

public abstract class Villain extends Character {
    public Villain(@NotNull String name) {
        super(name);
    }
}
