package com.example.crapsgame262.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    public enum Result { WIN_NATURAL, CRAPS, POINT_SET, WIN_POINT, LOSE_SEVEN, CONTINUE }

    private final Dice dice1 = new Dice();
    private final Dice dice2 = new Dice();
    private final List<Integer> history = new ArrayList<>();
    private GameListener listener = new GameAdapter();

    private int roll;
    private int point;      // 0 = no hay punto establecido
    private int wins;
    private int losses;

    public void setListener(GameListener listener) {
        this.listener = (listener != null) ? listener : new GameAdapter();
    }

    public Result play() {
        roll = dice1.roll() + dice2.roll();
        history.add(roll);

        Result result;
        if (point == 0) {                       // tiro de salida
            if (roll == 7 || roll == 11) {
                result = Result.WIN_NATURAL;
            } else if (roll == 2 || roll == 3 || roll == 12) {
                result = Result.CRAPS;
            } else {
                point = roll;
                result = Result.POINT_SET;
            }
        } else {                                // persiguiendo el punto
            if (roll == point) {
                result = Result.WIN_POINT;
            } else if (roll == 7) {
                result = Result.LOSE_SEVEN;
            } else {
                result = Result.CONTINUE;
            }
        }

        switch (result) {
            case WIN_NATURAL:
            case WIN_POINT:
                wins++;
                point = 0;
                listener.onWin(result, roll);
                break;
            case CRAPS:
            case LOSE_SEVEN:
                losses++;
                point = 0;
                listener.onLose(result, roll);
                break;
            case POINT_SET:
                listener.onPointSet(point);
                break;
            default:
                listener.onContinue(roll, point);
        }
        return result;
    }

    public Dice getDice1() { return dice1; }
    public Dice getDice2() { return dice2; }
    public int getRoll() { return roll; }
    public int getPoint() { return point; }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public List<Integer> getHistory() { return Collections.unmodifiableList(history); }
}
