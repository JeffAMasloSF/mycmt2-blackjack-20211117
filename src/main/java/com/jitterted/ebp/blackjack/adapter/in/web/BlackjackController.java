package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlackjackController {

    private final Game game;

    @Autowired
    public BlackjackController(Game game) {
        this.game = game;
    }

    @PostMapping("/start-game")
    public String startGame() {
        game.initialDeal();
        return redirectToPageBasedOnGameState();
    }

    @GetMapping("/game")
    public String gameView(Model model) {
        populateGameViewInto(model);
        return "blackjack";
    }

    @PostMapping("/hit")
    public String hitCommand() {
        game.playerHits();
        return redirectToPageBasedOnGameState();
    }

    @GetMapping("/done")
    public String doneView(Model model) {
        populateGameViewInto(model);
        model.addAttribute("outcome", game.determineOutcome().display());
        return "done";
    }

    @PostMapping("/stand")
    public String standCommand() {
        game.playerStands();
        return redirectToPageBasedOnGameState();
    }

    // MAPS Game State -> PAGE to show
    public String redirectToPageBasedOnGameState() {
        if (game.isPlayerDone()) {
            return "redirect:/done";
        } else {
            return "redirect:/game";
        }
    }

    private void populateGameViewInto(Model model) {
        model.addAttribute("gameView", GameView.of(game));
    }

}
