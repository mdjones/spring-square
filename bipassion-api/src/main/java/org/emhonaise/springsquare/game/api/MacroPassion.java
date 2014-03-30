package org.emhonaise.springsquare.game.api;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

/**
 *
 * Created by jonesmic on 3/27/14.
 */
public class MacroPassion {
    private Map<String, Passion> idToPassion;
    private final PassionStateStrategy passionStateStrategy;

    private PassionState passionState;
    private WithstandingState withstandingState;
    private double averageLevelRatio;

    protected MacroPassion(Builder builder) {
        this.passionStateStrategy = builder.passionStateStrategy;
    }

    public Iterable<Passion> getPassions() {
        return idToPassion.values();
    }

    public PassionState getPassionState() {
        return passionState;
    }

    public void setPassionState(PassionState passionState) {
        this.passionState = passionState;
    }

    public WithstandingState getWithstandingState() {
        return withstandingState;
    }

    public void setWithstandingState(WithstandingState withstandingState) {
        this.withstandingState = withstandingState;
    }

    public void setAverageLevelRatio(double averageLevelRatio) {
        this.averageLevelRatio = averageLevelRatio;
    }

    public double getAverageLevelRatio() {
        return averageLevelRatio;
    }

    public void actionApplied() {
         passionStateStrategy.execute(this);
    }

    private void setIdToPassion(Map<String, Passion> idToPassion) {
        this.idToPassion = idToPassion;
    }

    public Passion getPassion(String passionID) {
        return idToPassion.get(passionID);
    }

    public static class Builder{

        private final PassionStateStrategy passionStateStrategy;
        private Set<Passion.Builder> passionBuilders = Sets.newHashSet();

        public Builder(PassionStateStrategy passionStateStrategy) {
            this.passionStateStrategy = passionStateStrategy;
        }

        public MacroPassion build(){
            MacroPassion macroPassion = new MacroPassion(this);

            Map<String, Passion> idToPassion = Maps.newHashMap();

            for(Passion.Builder builder : passionBuilders){
                builder.macroPassion(macroPassion);
                idToPassion.put(builder.getID(), builder.build());
            }

            macroPassion.setIdToPassion(idToPassion);

            return macroPassion;
        }

        public Builder addPassionBuilder(Passion.Builder passionBuilder){
            passionBuilders.add(passionBuilder);
            return this;
        }
    }
}
