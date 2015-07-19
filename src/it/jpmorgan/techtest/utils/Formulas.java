package it.jpmorgan.techtest.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import it.jpmorgan.techtest.bean.Stock;
import it.jpmorgan.techtest.bean.StockType;
import it.jpmorgan.techtest.bean.Trade;

public class Formulas {

	public static double dividendYield(final double price, final Stock stock){

		double ret = 0.0;

		try{

			if(stock.getType().name().equals(StockType.Preferred.name())){

				ret = (stock.getFixedDividend()*stock.getParValue())/price;

			}else{

				ret = (stock.getLastDividend()/price);

			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return ret;
	}

	public static double peRatio(final double price, final Stock stock){

		double ret = 0.0;

		try{

			if(stock.getLastDividend()==0.00) return ret;

			else{
				ret = price/stock.getLastDividend();
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return ret;
	}

	public static double geometricMean(final List<Trade> tradeList){

		double ret = 1.0;

		try{

			if(!tradeList.isEmpty()){

				double result = 1.0;
				Iterator iter = tradeList.iterator();
				while(iter.hasNext()){
					Trade trade = (Trade)iter.next();
					result = result*(trade.getPrice());
				}

				ret = Math.pow(result, (1.0/tradeList.size()));
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return ret;
	}

	public static double stockPrice(final List<Trade> tradeList){

		double ret = 0.0;

		try{

			if(!tradeList.isEmpty()){

				Integer totQuantity = 0;
				double tempCalc = 1.0;
				Iterator iter = tradeList.iterator();

				while(iter.hasNext()){
					Trade trade = (Trade)iter.next();
					totQuantity+=trade.getQuantity();
					tempCalc = tempCalc*(trade.getPrice()*trade.getQuantity());
				}

				ret = tempCalc/totQuantity;

			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return ret;
	}
	
	public static double getRandomPrice(int min, int max){
		
		Random r = new Random();
		double randomPrice = min + (max - min) * r.nextDouble();
		
		return randomPrice;
		
	}

}
