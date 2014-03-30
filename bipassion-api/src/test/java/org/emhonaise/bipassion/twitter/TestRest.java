package org.emhonaise.bipassion.twitter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.social.twitter.api.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Created by jonesmic on 3/29/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class TestRest {
//
//    protected MockHttpSession session;
//    protected MockHttpServletRequest request;
//
//    protected void startSession() {
//        session = new MockHttpSession();
//    }
//
//    protected void endSession() {
//        session.clearAttributes();
//        session = null;
//    }
//
//    protected void startRequest() {
//        request = new MockHttpServletRequest();
//        request.setSession(session);
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//    }
//
//    protected void endRequest() {
//        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).requestCompleted();
//        RequestContextHolder.resetRequestAttributes();
//        request = null;
//    }

    @Resource
    private Twitter twitter;

    @Test
    public void testTwitter(){


        Pattern hashPattern = Pattern.compile("#\\w+");

        for(Tweet tweet : twitter.timelineOperations().getMentions()){

          //  System.out.println(tweet.getFromUser());
          //  System.out.println(tweet.getText());

            Matcher m = hashPattern.matcher(tweet.getText());
            while (m.find()){
                System.out.print(m.group());
                System.out.print(":");
            }
            System.out.println();
        }
    }
}
