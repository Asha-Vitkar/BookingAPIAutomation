package com.org.booking.utils;

import java.util.Random;

public class GenerateRandomNumber {
	
	static Random randomNum=new Random();
	public static int getRandomNumber()
	{
		int number=randomNum.nextInt(10);
		return number;
	}

}
