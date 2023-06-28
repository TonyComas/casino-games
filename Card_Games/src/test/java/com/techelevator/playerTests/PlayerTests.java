package com.techelevator.playerTests;

import games.player.Player;
import org.junit.*;

public class PlayerTests {

    private Player target;
    @Before
    public void setup(){
        target = new Player("Test");
    }

    @Test
    public void changeBalance_works_with_adding(){
        Assert.assertEquals(25, target.changeBalance(25));
    }

    @Test
    public void changeBalance_works_with_subtracting(){
        Assert.assertEquals(-10, target.changeBalance(-10));
    }
    @Test
    public void bet_removes_balance(){
        target.bet(20);
        Assert.assertEquals(-20, target.getBalance());
    }
}
