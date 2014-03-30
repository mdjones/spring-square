package org.emhonaise.springsquare.game.api;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.junit.Test;

/**
 *
 * Created by jonesmic on 3/27/14.
 */

public class TestPassion {

    @Test
    public void testParseVCF(){
        MacroPassion.Builder builder = new MacroPassion.Builder(new SimplePassionStateStrategy());

        Passion.Builder sciencePassion
                = new Passion.Builder("ZZZZ").description("I like Science").name("Science")
                .posSegments(10).negSegments(10);

        Passion.Builder musicPassion
                = new Passion.Builder("YYYY").description("I like Music").name("Music")
                .posSegments(3).negSegments(5);

        Passion.Builder gamesPassion
                = new Passion.Builder("XXXX").description("I like Games").name("Games")
                .posSegments(10).negSegments(3);

        builder.addPassionBuilder(sciencePassion).addPassionBuilder(musicPassion).addPassionBuilder(gamesPassion);
        org.emhonaise.springsquare.game.api.Character character = new Character(builder.build(), new TestThresholdStrategy());

        character.applyAction("ZZZZ", true);
        character.applyAction("YYYY", true);
        character.applyAction("XXXX", true);

        character.getThresholdStrategy().execute(character);

        character.applyAction("ZZZZ", false);
        character.getThresholdStrategy().execute(character);

        character.applyAction("ZZZZ", false);
        character.getThresholdStrategy().execute(character);

        character.applyAction("ZZZZ", false);
        character.applyAction("ZZZZ", false);
        character.applyAction("ZZZZ", false);
        character.applyAction("ZZZZ", false);  character.applyAction("ZZZZ", false);
        character.applyAction("ZZZZ", false);  character.applyAction("ZZZZ", false);  character.applyAction("ZZZZ", false);
        character.applyAction("ZZZZ", false);

        character.getThresholdStrategy().execute(character);

        character.applyAction("XXXX", false);
        character.applyAction("XXXX", false);

        character.getThresholdStrategy().execute(character);


    }

    @Test
    public void testAverage(){
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
        descriptiveStatistics.addValue(-0.1);
        descriptiveStatistics.addValue(-0.3333);
        descriptiveStatistics.addValue(-0.2);

        System.out.println(descriptiveStatistics.getPercentile(50));
    }

    @Test
    public void testAverage2(){

        double sum = 0;
        sum+=0.1;
        sum+=0.2;
        sum+=-0.9;

        double average = sum/3.0;

        System.out.println(average);
    }
}
