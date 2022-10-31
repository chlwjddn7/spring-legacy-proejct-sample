package kr.co.edu.test;

import java.util.HashMap;
import java.util.List;

import kr.co.edu.util.HtmlCrawlingUtil;
import kr.co.edu.util.HttpUtil;

public class Test {
	
	public static void main(String[] args) {
		
		TestClass2 testClass2 = new TestClass2();
		
		try {
			int result = testClass2.getTestClass().sum(1, 1);
			System.out.println("result : " + result);
			
			int result2 = testClass2.getTestClass().sum(1, 1);
			
			System.out.println("result2 : " + result2);
			
			int result3 = 3;
			System.out.println("result3 : " + result3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
