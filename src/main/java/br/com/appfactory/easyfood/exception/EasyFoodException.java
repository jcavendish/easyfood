package br.com.appfactory.easyfood.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class EasyFoodException extends Exception {
	
	private static final long serialVersionUID = 12333425L;

	public EasyFoodException(String string) {
		super(string);
	}

}
