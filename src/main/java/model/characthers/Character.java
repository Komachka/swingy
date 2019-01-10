package model.characthers;

import javax.validation.constraints.NotNull;

public abstract class Character {
    @NotNull
    private String name;

    public Character(@NotNull String name) {
        this.name = name;
    }
}
