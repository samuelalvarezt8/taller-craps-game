package com.example.crapsgame262.models;

public class GameAdapter implements GameListener {
    @Override public void onWin(Game.Result result, int roll) { }
    @Override public void onLose(Game.Result result, int roll) { }
    @Override public void onPointSet(int point) { }
    @Override public void onContinue(int roll, int point) { }
}
