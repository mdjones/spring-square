package org.emhonaise.springsquare.game.api;

import org.apache.log4j.Logger;

/**
 *
 * Created by jonesmic on 3/27/14.
 */
public class TestThresholdStrategy implements ThresholdStrategy {
    private final static Logger logger = Logger.getLogger(TestThresholdStrategy.class);

    public void execute(org.emhonaise.springsquare.game.api.Character character) {
        PassionState passionState = character.getMacroPassion().getPassionState();
        WithstandingState withstandingState = character.getMacroPassion().getWithstandingState();
        double ratio = character.getMacroPassion().getAverageLevelRatio();

        logger.info("Generally I feel " + passionState + " about you to the level " + ratio);
        logger.info("And our past makes me feel " + withstandingState);
    }
}
