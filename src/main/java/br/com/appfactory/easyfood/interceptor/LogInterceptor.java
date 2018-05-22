package br.com.appfactory.easyfood.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogInterceptor {

	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		long start = System.currentTimeMillis();

		Object o = context.proceed();

		System.out.println("[INFO] " + context.getTarget().getClass().getName() + " -> " + context.getMethod().getName()
				+ " expent " + (System.currentTimeMillis() - start) + " milliseconds to complete execution.");
		
		return o;
	}

}
