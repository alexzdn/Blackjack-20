package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
 * Created by michaelhilton on 1/25/16.
 */
public class Game {

    public java.util.List<Card> deck = new ArrayList<>();

    public java.util.List<java.util.List<Card>> cols = new ArrayList<>();
    public String dealersTurn;
    public Player p;

    public Game(){ //This needs to be changed first really. 3 columns, 0 for dealer, 1 for hand 1, 2 for hand 2
        cols.add(new ArrayList<Card>()); //0
        cols.add(new ArrayList<Card>()); //1
        cols.add(new ArrayList<Card>()); //2
        this.buildDeck();
        dealersTurn = "f";
        p = new Player();
    }

    public void split(){
        if(this.cols.get(1).get(0).getValue() == this.cols.get(1).get(1).getValue()); //if card 1 value == card 2
            this.move(1,2); //moves one of the cards over to the new stack
            //TODO BETTING FUNCTION GOES HERE
    }

    public void buildDeck() {
        for(int i = 2; i < 15; i++){
            deck.add(new Card(i,Suit.Clubs));
            deck.add(new Card(i,Suit.Hearts));
            deck.add(new Card(i,Suit.Diamonds));
            deck.add(new Card(i,Suit.Spades));
        }
    }

    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(deck, new Random(seed));
    }
    public void hit(int i){ //i is 0 for dealer, 1 for hand 1, 2 for hand 2 if split
        cols.get(i).add(deck.get(deck.size()-1));
        deck.remove(deck.size()-1);

    }
    public void dealHands() {
        hit(0);
        hit(0);
        hit(1);
        hit(1);
    }

    public void doubleDown(int i){

        p.bet();
        hit(i);
        //stay();
    }

    //customDeal to setup game for testing purposes
    public void customDeal(int c1, int c2, int c3, int c4) {
        cols.get(0).add(deck.get(c1));
        deck.remove(c1);
        cols.get(1).add(deck.get(c2));
        deck.remove(c2);
        cols.get(2).add(deck.get(c3));
        deck.remove(c3);
        cols.get(3).add(deck.get(c4));
        deck.remove(c4);
    }

    public void remove(int columnNumber) {
        if(colHasCards(columnNumber)) {
            Card c = getTopCard(columnNumber);
            boolean removeCard = false;
            for (int i = 0; i < 4; i++) {
                if (i != columnNumber) {
                    if (colHasCards(i)) {
                        Card compare = getTopCard(i);
                        if (compare.getSuit() == c.getSuit()) {
                            if (compare.getValue() > c.getValue()) {
                                removeCard = true;
                            }
                        }
                    }
                }
            }
            if (removeCard) {
                this.cols.get(columnNumber).remove(this.cols.get(columnNumber).size() - 1);
            }
        }
    }

    private boolean colHasCards(int colNumber) {
        return this.cols.get(colNumber).size() > 0;
    }

    private Card getTopCard(int columnNumber) {
        return this.cols.get(columnNumber).get(this.cols.get(columnNumber).size()-1);
    }
    public int deckSize(){
        return deck.size();
    }

    public void move(int colFrom, int colTo) {
        if (!colHasCards(colTo))
        {
            Card cardToMove = getTopCard(colFrom);
            this.removeCardFromCol(colFrom);
            this.addCardToCol(colTo, cardToMove);
        }
        else
        {
            System.out.println("Don\'t you dare to move that card");
        }
    }

    private void addCardToCol(int colTo, Card cardToMove) {
        cols.get(colTo).add(cardToMove);
    }

    private void removeCardFromCol(int colFrom) {
        this.cols.get(colFrom).remove(this.cols.get(colFrom).size()-1);

        //I think that should conflict...
        //Did it work yet?

    }
}
