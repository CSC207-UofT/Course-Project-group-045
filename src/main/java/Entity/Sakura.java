package Entity;

import Entity.Character;
import Controllers.Game;

public class Sakura extends Character {
    public Sakura() {
        super(new Object[]{"Sakura", 80, 10, 2, 5, 1, 0});
    }

    public void ultimate(Character target) {
        this.reset_Meter();
        for (int i = 0; i < Game.playerChar.size() ; i++){
            Game.playerChar.get(i).increaseCurrHealth(20);
            if (Game.playerChar.get(i).getCurrHealth() > Game.playerChar.get(i).getMaxHealth()){
                Game.playerChar.get(i).reduceCurrHealth(Game.playerChar.get(i).getCurrHealth() -
                        Game.playerChar.get(i).getMaxHealth());
            }
        }
        this.useAction();
    }
}