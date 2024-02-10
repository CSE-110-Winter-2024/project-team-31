package edu.ucsd.cse110.successorator.lib.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoalTest {

    Goal testGoal1 = new Goal("testGoal", 0); // first test
    Goal testGoal2 = new Goal(" ", 1000000000); // string with only 1 space and a large enough id
    Goal testGoal3 = new Goal("", 10); // string with only an empty string

    @Test
    public void getName() {
        assertEquals(testGoal1.getName(), "testGoal");
        assertEquals(testGoal2.getName(), " ");
        assertEquals(testGoal3.getName(), "");
    }

    @Test
    public void getId() {
        assertEquals(testGoal1.getId(), (Integer) 0);
        assertEquals(testGoal2.getId(),(Integer) 1000000000);
        assertEquals(testGoal3.getId(), (Integer) 10);
    }
}