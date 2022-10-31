package kr.co.edu.common.vo;

public class Test {
	
	public Test () {
		
	}
	
	public Test (int testSeq) {
		this.testSeq = testSeq;
	}

	private int testSeq;
    private String testTitle;
    private String testContent;
    
	public int getTestSeq() {
		return testSeq;
	}
	public void setTestSeq(int testSeq) {
		this.testSeq = testSeq;
	}
	public String getTestTitle() {
		return testTitle;
	}
	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}
	public String getTestContent() {
		return testContent;
	}
	public void setTestContent(String testContent) {
		this.testContent = testContent;
	}
}
