package it.jpmorgan.techtest.utils;

public class DivideByZeroException extends ArithmeticException {
	
	String message;

	public DivideByZeroException( String message )
	{
		this.message = message;
	}

	public String toString()
	{
		return( message );
	}

}
