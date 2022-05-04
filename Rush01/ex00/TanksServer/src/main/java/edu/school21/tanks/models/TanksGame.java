package edu.school21.tanks.models;

import java.util.Objects;

public class TanksGame {
    private Long identifier;

    public TanksGame() {
        this.identifier = null;
    }

    public TanksGame(Long identifier) {
        this.identifier = identifier;
    }

    public Long getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        TanksGame tanksGame = (TanksGame) object;

        return Objects.equals(this.identifier, tanksGame.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.identifier);
    }

    @Override
    public String toString() {
        return "TanksGame : { identifier=" + this.identifier + " }";
    }
}
