package org.emhonaise.springsquare.game.api;

/**
 *
 * Created by jonesmic on 3/27/14.
 */
public class Character {

    private final MacroPassion macroPassion;
    private final ThresholdStrategy thresholdStrategy;

    public Character(MacroPassion macroPassion, ThresholdStrategy thresholdStrategy) {
        this.macroPassion = macroPassion;
        this.thresholdStrategy = thresholdStrategy;
    }

    public MacroPassion getMacroPassion() {
        return macroPassion;
    }

    public ThresholdStrategy getThresholdStrategy() {
        return thresholdStrategy;
    }

    public void applyAction(String passionID, boolean positive) {
        macroPassion.getPassion(passionID).applyAction(positive);
    }
}
