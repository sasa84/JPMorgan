package it.jpmorgan.techtest.junit;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import it.jpmorgan.techtest.bean.Stock;
import it.jpmorgan.techtest.bean.StockSymbol;
import it.jpmorgan.techtest.bean.StockType;
import it.jpmorgan.techtest.bean.Trade;
import it.jpmorgan.techtest.utils.DivideByZeroException;
import it.jpmorgan.techtest.utils.Formulas;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestCase {
	
	static Integer rangeMin;
	static Integer rangeMax;
	
	@BeforeClass
	public static void setValues(){
		rangeMin = 1;
		rangeMax = 100;
		
	}

	@Test
	public void dividendYeldCommon() {
		
		double randomPrice = getRandomPrice();
		
		Stock stock = new Stock(StockSymbol.TEA,StockType.Common,0.0,0,100.0);
		
		final double expectedValue = stock.getLastDividend()/randomPrice;
		
		assertEquals("Test Dividend Yeld for Common Stock Type",expectedValue,Formulas.dividendYield(randomPrice, stock),0.001);
		
		
	}
	
	@Test
	public void dividendYeldPreferred() {
		
		double randomPrice = getRandomPrice();
		
		Stock stock = new Stock(StockSymbol.GIN,StockType.Preferred,8.0,2,100.0);
		
		final double expectedValue = (stock.getFixedDividend()*stock.getParValue())/randomPrice;
		
		assertEquals("Test Dividend Yeld for Preferred Stock Type",expectedValue,Formulas.dividendYield(randomPrice, stock),0.001);
		
		
	}
	
	@Test
	public void peRatio() {
		
		double randomPrice = getRandomPrice();
		
		Stock stock = new Stock(StockSymbol.GIN,StockType.Preferred,8.0,2,100.0);
		
		final double expectedValue = randomPrice/stock.getLastDividend();
		
		assertEquals("Test P/E Ratio",expectedValue,Formulas.peRatio(randomPrice, stock),0.001);
		
		
	}
	
	@Test(expected=DivideByZeroException.class)
	public void peRatioWithLastDividendZero() {		

		double randomPrice = getRandomPrice();

			Stock stock = new Stock(StockSymbol.TEA,StockType.Common,0,0,100.0);
			double denominator = stock.getLastDividend();
			if ( denominator == 0 )
				throw new DivideByZeroException("You can't divide by zero.");

			final double expectedValue = randomPrice/stock.getLastDividend();

			assertEquals("Test P/E Ratio",expectedValue,Formulas.peRatio(randomPrice, stock),0.001);
		
	}
	
	@Test
	public void geometricMean() {		
		
		try{

			List<Trade> tradeList = generateRandomTrades(10,true,5);
			
			double expectedValue = 1.0;
			if(!tradeList.isEmpty()){

				double result = 1.0;
				Iterator iter = tradeList.iterator();
				while(iter.hasNext()){
					Trade trade = (Trade)iter.next();
					result = result*(trade.getPrice());
				}

				expectedValue = Math.pow(result, (1.0/tradeList.size()));
			}

			assertEquals("Test Geometric Mean",expectedValue,Formulas.geometricMean(tradeList),0.001);

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void stockPrice() {
		
		try{
			
			List<Trade> tradeList = generateRandomTrades(10,true,5);
			double expectedValue = 0.0;
			
			if(!tradeList.isEmpty()){

				Integer totQuantity = 0;
				double tempCalc = 1.0;
				Iterator iter = tradeList.iterator();

				while(iter.hasNext()){
					Trade trade = (Trade)iter.next();
					totQuantity+=trade.getQuantity();
					tempCalc = tempCalc*(trade.getPrice()*trade.getQuantity());
				}

				expectedValue = tempCalc/totQuantity;
				//expectedValue = 1;

			}
			
			assertEquals("Test Geometric Mean",expectedValue,Formulas.stockPrice(tradeList),0.001);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public static List<Trade> generateRandomTrades(int listLength, boolean isBuy, Integer quantity){
		
		List<Trade> list = new ArrayList<Trade>();
		
		for(int i=0;i<listLength;i++){
			Trade trade = generateRandomTrade(isBuy, quantity);
			list.add(trade);
		}
		
		return list;
	}
	
	public static Trade generateRandomTrade(boolean isBuy, Integer quantity){
	
		double randomPrice = getRandomPrice();
		Date time = new Date();
		
		Trade trade = new Trade(time,quantity,randomPrice,isBuy);
		
		return trade;
	}
	
	public static double getRandomPrice(){
		
		Random r = new Random();
		double randomPrice = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		
		return randomPrice;
		
	}

}
