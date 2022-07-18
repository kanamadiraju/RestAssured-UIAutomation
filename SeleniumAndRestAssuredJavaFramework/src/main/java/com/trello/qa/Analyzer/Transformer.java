package com.trello.qa.Analyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class Transformer implements IAnnotationTransformer {
	
	public void transform(ITestAnnotation annotations, Class testClass, Constructor testConstructor, Method testMethod) 
	{
		annotations.setRetryAnalyzer(RetryAnalyzer.class);
		
	}

}
