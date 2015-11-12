package app.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Audience {

	// Declaring a frequently used pointcut expression with @Pointcut
	@Pointcut("execution(* app.beans.Performance.perform(..))")
	public void performance() {}

	@Before("execution(* app.beans.Performance.perform(..))")
	public void takeSeats() {
		System.out.println("@Before: Taking seats");
	}

	@After("execution(* app.beans.Performance.perform(..))")
	public void applause() {
		System.out.println("@After: CLAP CLAP CLAP!!!");
	}

	@AfterReturning("execution(* app.beans.Performance.perform(..))")
	public void applauseAfterReturning() {
		System.out.println("@AfterReturning: CLAP CLAP CLAP!!!");
	}

	@AfterThrowing("execution(* app.beans.Performance.perform(..))")
	public void demandRefund() {
		System.out.println("@AfterThrowing: Demanding a refund");
	}

	@Around("performance()")
	public void watchPerformance(ProceedingJoinPoint jp) {
		try {
			System.out.println("@Around: Taking seats");
			jp.proceed();
			System.out.println("@Around: CLAP CLAP CLAP!!!");
		} catch (Throwable e) {
			System.out.println("@Around: Exception");
		}
	}

	@Before("execution(* app.beans.Performance.performSong(String)) && args(song)")
	public void befooreParams(String song) {
		System.out.println(song + " will be preformed soon...");
	}

}

