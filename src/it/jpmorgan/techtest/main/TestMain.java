package it.jpmorgan.techtest.main;

import java.util.List;

import it.jpmorgan.techtest.bean.Stock;
import it.jpmorgan.techtest.bean.StockSymbol;
import it.jpmorgan.techtest.bean.StockType;
import it.jpmorgan.techtest.bean.Trade;
import it.jpmorgan.techtest.junit.TestCase;
import it.jpmorgan.techtest.utils.Formulas;
import it.jpmorgan.techtest.utils.TradesGenerator;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try{
			
			int min = 1;
			int max = 100;
			
			Stock stockCommon = new Stock(StockSymbol.TEA,StockType.Common,0,0,100.0);
			double randomPrice = Formulas.getRandomPrice(min, max);
			double dividendYeld = Formulas.dividendYield(randomPrice, stockCommon);
			System.out.println("The dividend yeld for the given stock common type is: "+dividendYeld);
			
			Stock stockPreferred = new Stock(StockSymbol.GIN,StockType.Preferred,8.0,2,100.0);
			randomPrice = Formulas.getRandomPrice(min, max);
			dividendYeld = Formulas.dividendYield(randomPrice, stockPreferred);
			System.out.println("The dividend yeld for the given stock common type is: "+dividendYeld);
			
			randomPrice = Formulas.getRandomPrice(min, max);
			double peRatio = Formulas.peRatio(randomPrice, stockPreferred);
			System.out.println("The P/E Ratio for the given stock is: "+peRatio);
			
			List<Trade> tradeList = TradesGenerator.generateRandomTrades(10,true,5, min, max);
			double geometricMean = Formulas.geometricMean(tradeList);
			System.out.println("The Geometric Mean for the given trade list is: "+geometricMean);
			
			double stockPrice = Formulas.stockPrice(tradeList);
			System.out.println("The Stock Price for the given trade list is: "+stockPrice);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		Result result = JUnitCore.runClasses(TestCase.class);
	    for (Failure failure : result.getFailures()) {
	      System.out.println(failure.toString());
	    }

	}

}
