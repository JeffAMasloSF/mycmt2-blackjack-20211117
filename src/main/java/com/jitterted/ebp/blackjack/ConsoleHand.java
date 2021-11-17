package com.jitterted.ebp.blackjack;

public class ConsoleHand {

    // TRANSFORM DOMAIN (Hand) to I/O-CONSOLE (String)
    static String displayFirstCard(Hand hand) {
        return ConsoleCard.display(hand.firstCard());
    }
}
