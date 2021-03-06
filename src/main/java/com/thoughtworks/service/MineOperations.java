package com.thoughtworks.service;

import com.thoughtworks.model.Mine;
import com.thoughtworks.model.MineField;
import com.thoughtworks.model.Point;

/**
 * Created by amarendra on 07/09/16.
 */
public class MineOperations {

    private MineField mineField;
    private IPlayerOperations playerOperations;

    public MineOperations(MineField mineField, IPlayerOperations playerOperations) {
        this.mineField = mineField;
        this.playerOperations = playerOperations;
    }

    public boolean playerChance(Point point, String inputString){

        boolean matchFound = false;
        if(inputString.contains("o")){
            playerOperations.open(mineField, point);
            //Check for Matching Mine
            matchFound = checkForMatchingMine(point);
        }
        else if(inputString.contains("f")){
            playerOperations.flag(mineField, point);
        }


        if(matchFound){
            playerOperations.getPlayer().setStatus(false);
            mineField.setStatus(true);
            return true;
        } else if(checkForMineFieldExhausted()){
            playerOperations.getPlayer().setStatus(true);
            mineField.setStatus(false);
            return true;
        }
        else {
            return false;
        }

    }

    private boolean checkForMineFieldExhausted() {
        for(int col =0 ; col<mineField.getFieldArea().length; col++)
        {
            for(int row = 0; row<mineField.getFieldArea().length; row++)
            {
                if("x".equals(mineField.getFieldArea()[row][col])){
                    return false;
                }
            }
        }
        return true;

    }

    private boolean checkForMatchingMine(Point point) {

        for(int col =0 ; col<mineField.getFieldArea().length; col++)
        {
            for(int row = 0; row<mineField.getFieldArea().length; row++)
            {
                if(mineField.getMines().contains(new Mine(point))){
                    return true;
                }
            }
        }

        return false;
    }

    public void printMineField(){

        for(int col =0 ; col < mineField.getFieldArea().length; col++)
        {
            for(int row = 0; row < mineField.getFieldArea().length; row++)
            {

                System.out.print(mineField.getFieldArea()[col][row] + " ");
            }

            System.out.print("\n");
        }

    }
}
