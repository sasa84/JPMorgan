package it.jpmorgan.techtest.utils;

import it.jpmorgan.techtest.bean.Trade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradesGenerator {
	
	public static List<Trade> generateRandomTrades(int listLength, boolean isBuy, Integer quantity, int min, int max){
		
		List<Trade> list = new ArrayList<Trade>();
		
		for(int i=0;i<listLength;i++){
			Trade trade = generateRandomTrade(isBuy, quantity, min, max);
			list.add(trade);
		}
		
		return list;
	}
	
	public static Trade generateRandomTrade(boolean isBuy, Integer quantity, int min, int max){
	
		double randomPrice = Formulas.getRandomPrice(min, max);
		Date time = new Date();
		
		Trade trade = new Trade(time,quantity,randomPrice,isBuy);
		
		return trade;
	}

}
