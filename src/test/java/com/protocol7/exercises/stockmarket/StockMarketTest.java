package com.protocol7.exercises.stockmarket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.protocol7.exercises.stockmarket.StockMarket;
import com.protocol7.exercises.stockmarket.StockMarket.Delta;


public class StockMarketTest {

    private static final int FORECAST_MIN = 1;
    private static final int FORECAST_MAX = 1000;
    private static final int SIZE = 10;
    
    @Test
    public void test1() {
        List<Integer> forecast = Arrays.asList(837, 365, 172, 778, 897, 294, 905, 605, 467, 67);
        
        StockMarket stockMarket = new StockMarket();
        Delta actual = stockMarket.predict(forecast);
        
        Assert.assertEquals(2, actual.startPos);
        Assert.assertEquals(6, actual.endPos);
        Assert.assertEquals(172, actual.startValue);
        Assert.assertEquals(905, actual.endValue);
        Assert.assertEquals(733, actual.delta());
    }

    @Test
    public void test2() {
        List<Integer> forecast = Arrays.asList(2, 9, 1, 4);
        
        StockMarket stockMarket = new StockMarket();
        Delta actual = stockMarket.predict(forecast);
        
        Assert.assertEquals(0, actual.startPos);
        Assert.assertEquals(1, actual.endPos);
        Assert.assertEquals(2, actual.startValue);
        Assert.assertEquals(9, actual.endValue);
        Assert.assertEquals(7, actual.delta());
    }

    @Test
    public void test3() {
        List<Integer> forecast = Arrays.asList(2, 9, 1, 4, 0, 3);
        
        StockMarket stockMarket = new StockMarket();
        Delta actual = stockMarket.predict(forecast);
        
        Assert.assertEquals(0, actual.startPos);
        Assert.assertEquals(1, actual.endPos);
        Assert.assertEquals(2, actual.startValue);
        Assert.assertEquals(9, actual.endValue);
        Assert.assertEquals(7, actual.delta());
    }

    @Test
    public void emptyForecast() {
        List<Integer> forecast = Arrays.asList();
        
        StockMarket stockMarket = new StockMarket();
        Assert.assertNull(stockMarket.predict(forecast));
    }

    @Test
    public void singleValueForecast() {
        List<Integer> forecast = Arrays.asList(1);
        
        StockMarket stockMarket = new StockMarket();
        Delta actual = stockMarket.predict(forecast);
        
        Assert.assertEquals(0, actual.startPos);
        Assert.assertEquals(0, actual.endPos);
        Assert.assertEquals(1, actual.startValue);
        Assert.assertEquals(1, actual.endValue);
        Assert.assertEquals(0, actual.delta());

    }

    @Test
    public void random() {
        // generate a random forecast
        List<Integer> forecast = new ArrayList<Integer>();
        Random rnd = new Random();
        
        for(int i = 0; i<SIZE; i++) {
            forecast.add(rnd.nextInt(FORECAST_MAX - FORECAST_MIN) + FORECAST_MIN);
        }
        
        System.out.println(forecast);

    }
}
