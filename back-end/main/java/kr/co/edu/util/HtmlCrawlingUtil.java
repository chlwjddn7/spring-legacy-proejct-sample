package kr.co.edu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * HTML(Text)을 Dom(Document Object Model)형태로 파싱(변형)하여 필요한 값을 꺼내기 위한 유틸
 * 
 * @author 최정우
 * @since 2022.10.20
 */
public class HtmlCrawlingUtil {

	/**
	 * HTML(Text)을 Jsoup 라이브러리를 활용하여 Dom(Document Object Model)형태로 파싱(변형)한 후, 필요한 값을 꺼내기 위한 메서드
	 * 
	 * @author 최정우
	 * @since 2020.07.21
	 * @return HashMap(Data Name:Tag Value)
	 * 
	 * 필수: Jsoup 라이브러리
	 */
	public HashMap<String, String> getDataInHtml (String htmlText, Map<String, String> targetTags) {
		HashMap<String, String> result = new HashMap<>();
		
		//Jsoup의 Text to HTML 파싱 기능을 통해 Dom(Document Object Model)형태로 변형 한 뒤, Document 객체 가지고오기
		Document document = Jsoup.parse(htmlText);
		
		for (String targetTagTitle : targetTags.keySet()) {
			//System.out.println("targetTagTitle : " + targetTagTitle);
			String cssQuery = targetTags.get(targetTagTitle);
			Element element = document.selectFirst(cssQuery);
			String targetTagData = element.text();
			result.put(targetTagTitle, targetTagData);
		}
		
		return result;
	}
	
	/**
	 * HTML(Text)을 Jsoup 라이브러리를 활용하여 Dom(Document Object Model)형태로 파싱(변형)한 후, 필요한 값을 꺼내기 위한 메서드
	 * 
	 * @author 최정우
	 * @since 2020.07.21
	 * @return HashMap(Data Name:Tag Value)
	 * 
	 * 필수: Jsoup 라이브러리
	 */
	public ArrayList<String> getDataListInHtmlAttr (String htmlText, String targetTag, String targetAttr) {
		ArrayList<String> result = new ArrayList<>();
		
		//Jsoup의 Text to HTML 파싱 기능을 통해 Dom(Document Object Model)형태로 변형 한 뒤, Document 객체 가지고오기
		Document document = Jsoup.parse(htmlText);
		System.out.print(document.toString());
		
		Elements elements = document.select(targetTag);
		System.out.print(targetTag + ", elements.size(): " + elements.size());
		
		for (Element element : elements) {
			String value = element.attr(targetAttr);
			result.add(value);
		}
		
		return result;
	}
}
