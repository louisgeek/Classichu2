package com.classichu.classichu2.tool;

import java.util.Random;

/**
 * Created by louisgeek on 2017/3/18.
 */

public class RandomTool {
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
