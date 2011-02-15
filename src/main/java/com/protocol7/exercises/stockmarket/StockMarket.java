package com.protocol7.exercises.stockmarket;

import java.util.List;

public class StockMarket {

    public Delta predict(List<Integer> forecast) {
        if(forecast.isEmpty()) return null;
        
        Delta current = new Delta(0, forecast.get(0));
        Delta best = current;
        
        for(int i = 0; i<forecast.size(); i++) {
            int f = forecast.get(i);
            if(f > current.endValue) {
                current.updateEnd(i, f);
            }
            
            if(f < current.startValue) {
                if(current.delta() > best.delta()) best = current;
                
                current = new Delta(i, f);
            }
        }

        if(current.delta() > best.delta()) best = current;
        
        return best;
    }

    public static class Delta {
        public int startPos;
        public int startValue = Integer.MAX_VALUE;
        public int endPos;
        public int endValue = Integer.MIN_VALUE;
        
        public Delta(int initPos, int initValue) {
            startPos = initPos;
            startValue = initValue;
            endPos = initPos;
            endValue = initValue;
        }
        
        public void updateEnd(int pos, int value) {
            endPos = pos;
            endValue = value;
        }
        
        public int delta() {
            return endValue - startValue;
        }
        
    }
}
