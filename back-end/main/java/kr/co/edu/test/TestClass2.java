package kr.co.edu.test;

public class TestClass2 {
	
	TestClass testClass = null;
	public TestClass getTestClass() {
		if (testClass == null) {
			testClass = new TestClass();
		}
		return testClass;
	}
}
