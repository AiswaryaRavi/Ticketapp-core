package com.aiswarya.util;

import com.aiswarya.exception.ValidationException;

public class ValidationUtil {
	public static void isInvalid(String name,String msg)throws ValidationException{
	if(name==null || "".equals(name.trim()))
	{
		throw new ValidationException("Invaild"+msg);
	}
		
	
}

public static void isInvalid(Integer number,String msg)throws ValidationException{
	if(number==null || "".equals(number) || number<=0)
	{
		throw new ValidationException("Invaild"+msg);
	}
}

}
