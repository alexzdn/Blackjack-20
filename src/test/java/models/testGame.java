package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michaelhilton on 1/25/16.
 */
public class testGame {

    Game g = new Game();
    Player p = new Player();

    @Test
    public void testGameCreation(){
        //Game g = new Game();
        assertNotNull(g);
    }
    @Test
    public void testDeckCount(){
       Game g = new Game();
       assertEquals(52,g.deck.size());
        g.remove(2);
        assertEquals(51,g.deck.size());
    }


    @Test
    public void testGameBuildDeck(){
        //Game g = new Game();
        g.buildDeck();
        assertEquals(52,g.deck.size());
    }

    @Test
    public void testGameInit(){
       // Game g = new Game();
        g.buildDeck();
        g.shuffle();
        assertNotEquals(2,g.deck.get(0).getValue()); //this might fail randomly, should be improved
    }

    @Test
    public void testCustomDeal(){
        //Game g = new Game();
        g.buildDeck();
//        g.customDeal(0,3,6,9); Still not sure how this works, nor do we actually use it for anything
//        assertEquals("2Clubs",g.cols.get(0).get(0).toString());
//        assertEquals("3Clubs",g.cols.get(1).get(0).toString());
//        assertEquals("4Clubs",g.cols.get(2).get(0).toString());
//        assertEquals("5Clubs",g.cols.get(3).get(0).toString());
    }


    @Test
    public void testSplit(){
        //Game g = new Game();
        g.buildDeck();
        g.dealHands();
        g.p.split(g);
        assert(g.cols.get(2).size() == 0); //should be empty as that split is illegal
    }


    //This needs improvement
    @Test
    public void testHit()
    {
        int i = 1;
        g.hit(i);
        assert(g.deck.size() ==(g.deck.size()-1));
        assert(g.cols.get(i).size() == 1);
    }

    @Test
    public void testDoubleDown()
    {
        int i = 1;
        int testBetAmount = 2;
        p.doubleDown(i,g);
        assert(p.money == (p.money - (2*testBetAmount)));
        assert(p.isBet == (p.isBet + testBetAmount));
        assert(g.deck.size() ==(g.deck.size()-1));
    }
}
