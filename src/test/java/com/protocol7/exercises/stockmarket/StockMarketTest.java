package com.protocol7.exercises.stockmarket;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.protocol7.exercises.stockmarket.StockMarket.Delta;


public class StockMarketTest {

    private StockMarket stockMarket = new StockMarket();

    @Test
    public void currentBest() {
        List<Integer> forecast = Arrays.asList(2, 3, 1, 9);
        
        Delta actual = stockMarket.predict(forecast);
        
        Assert.assertEquals(2, actual.getStartPos());
        Assert.assertEquals(3, actual.getEndPos());
        Assert.assertEquals(1, actual.getStartValue());
        Assert.assertEquals(9, actual.getEndValue());
        Assert.assertEquals(8, actual.delta());
    }
    
    @Test
    public void currentNotBest() {
        List<Integer> forecast = Arrays.asList(2, 9, 1, 4);
        
        Delta actual = stockMarket.predict(forecast);
        
        Assert.assertEquals(0, actual.getStartPos());
        Assert.assertEquals(1, actual.getEndPos());
        Assert.assertEquals(2, actual.getStartValue());
        Assert.assertEquals(9, actual.getEndValue());
        Assert.assertEquals(7, actual.delta());
    }

    @Test
    public void previousBestShouldNotBeReplaced() {
        List<Integer> forecast = Arrays.asList(2, 9, 1, 4, 0, 3);
        
        Delta actual = stockMarket.predict(forecast);
        
        Assert.assertEquals(0, actual.getStartPos());
        Assert.assertEquals(1, actual.getEndPos());
        Assert.assertEquals(2, actual.getStartValue());
        Assert.assertEquals(9, actual.getEndValue());
        Assert.assertEquals(7, actual.delta());
    }

    @Test
    public void emptyForecast() {
        List<Integer> forecast = Arrays.asList();
        
        Assert.assertNull(stockMarket.predict(forecast));
    }

    @Test
    public void singleValueForecast() {
        List<Integer> forecast = Arrays.asList(1);
        
        Delta actual = stockMarket.predict(forecast);
        
        Assert.assertEquals(0, actual.getStartPos());
        Assert.assertEquals(0, actual.getEndPos());
        Assert.assertEquals(1, actual.getStartValue());
        Assert.assertEquals(1, actual.getEndValue());
        Assert.assertEquals(0, actual.delta());

    }
}
