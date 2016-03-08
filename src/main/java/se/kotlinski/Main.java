package se.kotlinski;

import se.kotlinski.config.PreSetter;
import se.kotlinski.controller.GameController;
import se.kotlinski.models.Game;

public class Main {

  public static void main(String[] args) {

    PreSetter preSetter = new PreSetter();

    Game game = preSetter.createGame();

    GameController gameController = new GameController(game);

    try {
      boolean someResult = gameController.startGame();
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
