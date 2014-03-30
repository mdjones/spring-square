package org.emhonaise.springsquare.game.api;

import com.google.common.collect.EnumMultiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import org.apache.log4j.Logger;

/**
 * If all passions are completely negative then make then the character will be unforgiving.
 * If the character has ever been unforgiving then they will upgrade to reserved until the median average is > 0.25
 * and forgiving until the median average is > 0.75
 *
 * Created by jonesmic on 3/27/14.
 */
public class SimplePassionStateStrategy implements PassionStateStrategy {
    private final static Logger logger = Logger.getLogger(SimplePassionStateStrategy.class);


    public void execute(MacroPassion macroPassion) {
        macroPassion.setAverageLevelRatio(getAverageLevelRatio(macroPassion));
        macroPassion.setPassionState(getStateByRatio(macroPassion));
        macroPassion.setWithstandingState(getWithstandingState(macroPassion));
    }

    private WithstandingState getWithstandingState(MacroPassion macroPassion) {
        WithstandingState currentWithstandingState = macroPassion.getWithstandingState();
        double averageLevelRatio = macroPassion.getAverageLevelRatio();

        if(averageLevelRatio<0){
            PassionState consensusState = getConsensusState(macroPassion);
            if(consensusState == PassionState.Negative){
                return WithstandingState.UnForgiving;
            }
        }else if(averageLevelRatio>0.75){
            if(currentWithstandingState == WithstandingState.UnForgiving
                    || currentWithstandingState == WithstandingState.Forgiving){
                return WithstandingState.Forgiving;
            }
        }else if(averageLevelRatio>0.25){
            if(currentWithstandingState == WithstandingState.UnForgiving){
                return WithstandingState.Reserved;
            }
        }

        return currentWithstandingState == null ? WithstandingState.Neutral : currentWithstandingState;
    }

    private double getAverageLevelRatio(MacroPassion macroPassion) {
        double sum = 0;
        int n = 0;
        for(Passion passion : macroPassion.getPassions()){
            n++;
            int level = passion.getPassionLevel();

            double value = 0.0;

            if(level>0){
                value = (double)level/(double)passion.getPosSegments();
            }else if(level<0){
                value = (double)level/(double)passion.getNegSegments();
            }

            sum+=value;
        }

        return sum/(double)n;
    }



    private PassionState getStateByRatio(MacroPassion macroPassion) {
        double ratio = macroPassion.getAverageLevelRatio();

        if(ratio>0){
            return PassionState.Positive;
        }else if(ratio<0){
            return PassionState.Negative;
        }else{
            return PassionState.Neutral;
        }
    }

    private PassionState getConsensusState(MacroPassion macroPassion) {
        Multiset<PassionState> set = EnumMultiset.create(PassionState.class);
        for(Passion passion : macroPassion.getPassions()){
            set.add(getPassionState(passion));
        }

        ImmutableMultiset<PassionState> highestCountFirst = Multisets.copyHighestCountFirst(set);
        return highestCountFirst.iterator().next();
    }

    private PassionState getPassionState(Passion passion) {
        int level = passion.getPassionLevel();
        if(level==0) return PassionState.Neutral;
        if(level<0) return PassionState.Negative;
        return PassionState.Positive;
    }

}
