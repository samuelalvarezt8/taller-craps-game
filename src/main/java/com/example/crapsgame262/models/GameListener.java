package com.example.crapsgame262.models;

public interface GameListener {
    void onWin(Game.Result result, int roll);
    void onLose(Game.Result result, int roll);
    void onPointSet(int point);
    void onContinue(int roll, int point);
}
