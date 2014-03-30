package org.emhonaise.springsquare.game.api;

import org.springframework.util.Assert;

/**
 *
 * Created by jonesmic on 3/27/14.
 */
public class Passion {

    /**
     * This represents the number of total positive actions that can be applied to a passion and by proxy the
     * significance of the action. If there are few segments then every action has a strong effect.
     */
    private final int posSegments;
    /**
     *  similar to posSegments
     */
    private final int negSegments;
    private final String id;
    private final String name;
    private final String description;
    /**
     * The macroPassion holding this passion.
     */
    private final MacroPassion macroPassion;

    private int passionLevel=0;

    private Passion(Builder builder) {
        this.posSegments = builder.posSegments;
        this.macroPassion = builder.macroPassion;
        this.negSegments = builder.negSegments;
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }

    public void applyAction(boolean positive){
        if(positive && passionLevel<posSegments){
            passionLevel++;
        }else if(passionLevel>-negSegments){
            passionLevel--;
        }

        macroPassion.actionApplied();
    }

    public int getPosSegments() {
        return posSegments;
    }

    public int getNegSegments() {
        return negSegments;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public int getPassionLevel() {
        return passionLevel;
    }

    public static class Builder{
        private  int posSegments;
        private  int negSegments;
        private  String id;
        private  String name;
        private  String description;
        private  MacroPassion macroPassion;

        public Builder(String id) {
            this.id = id;
        }

        public Passion build(){
            Assert.notNull(macroPassion, "No Associated MacroPassion");
            return new Passion(this);
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder posSegments(int posSegments){
            this.posSegments = posSegments;
            return this;
        }

        public Builder negSegments(int negSegments){
            this.negSegments = negSegments;
            return this;
        }

        public Builder  macroPassion(MacroPassion macroPassion){
            this.macroPassion = macroPassion;
            return this;
        }

        public String getID() {
            return id;
        }
    }
}
