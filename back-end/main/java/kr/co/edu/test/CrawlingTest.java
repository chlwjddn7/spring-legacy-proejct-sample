package kr.co.edu.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.edu.util.HtmlCrawlingUtil;
import kr.co.edu.util.HttpUtil;

public class CrawlingTest {

	public static void main(String[] args) {
		try {
			HttpUtil httpUtil = new HttpUtil();
			HtmlCrawlingUtil htmlCrawlingUtil = new HtmlCrawlingUtil();
			
			List<HashMap<String, String>> crawlingResultList = new ArrayList<>();
			
			/*이거는 안됨 -> 축구 목록 데이터가 없음(비동기)
			String requestURL = "https://sports.news.naver.com/wfootball/news/index";
			HashMap<String, Object> params = new HashMap<>(3);
			params.put("page", 1);
			params.put("date", "20221031");
			params.put("isphoto", "N");
			String responseBody = httpUtil.httpRequest(requestURL, null, params);
			
			System.out.println();
			System.out.println("------------ HTTP Request URL : " + requestURL + " ------------");
			System.out.println(responseBody);
			System.out.println("------------ HTTP Request End ------------");
			
			String targetTag = "#_newsList > ul > li > div > a";//
			String targetAttr = "href";
			List<String> linkList = htmlCrawlingUtil.getDataListInHtmlAttr(responseBody, targetTag, targetAttr);
			for (int i = 0; i < linkList.size(); i++) {
				System.out.println("href : " + linkList.get(i));
			}
			*/
			
			String requestURL = "https://sports.news.naver.com/wfootball/news/list";
			HashMap<String, Object> requestParams = new HashMap<>(3);
			requestParams.put("page", 1);
			requestParams.put("date", "20221031");
			requestParams.put("isphoto", "N");
			String responseBody = httpUtil.httpRequest(requestURL, null, requestParams);
			System.out.println();
			System.out.println("------------ HTTP Request URL : " + requestURL + " ------------");
			System.out.println(responseBody);
			System.out.println("------------ HTTP Request End ------------");
			
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> wfootballNewListData = mapper.readValue(responseBody, new TypeReference<HashMap<String,Object>>() {});
			ArrayList<HashMap<String, Object>> wfootballNewList = (ArrayList<HashMap<String, Object>>) wfootballNewListData.get("list");
			System.out.println(mapper.writeValueAsString(wfootballNewList));
			for (int i = 0; i < wfootballNewList.size(); i++) {
				String oid = (String) wfootballNewList.get(i).get("oid");
				String aid = (String) wfootballNewList.get(i).get("aid");
				
				String detailRequestURL = "https://sports.news.naver.com/news";
				HashMap<String, Object> detailRequestParams = new HashMap<>(2);
				detailRequestParams.put("oid", oid);
				detailRequestParams.put("aid", aid);
				String detailResponseBody = httpUtil.httpRequest(detailRequestURL, null, detailRequestParams);
				System.out.println();
				System.out.println("------------ HTTP Detail Request(index:"+i+") URL : " + requestURL + " ------------");
				System.out.println(detailResponseBody);
				System.out.println("------------ HTTP Detail Request(index:\"+i+\") End ------------");
				
				HashMap<String, String> targetTags = new HashMap<>(); 
				targetTags.put("newTitle", "#content > div > div.content > div > div.news_headline > h4");
				targetTags.put("newContent", "#newsEndContents");
				targetTags.put("newInsertDateTime", "#content > div > div.content > div > div.news_headline > div > span:nth-child(1)");
				targetTags.put("newInsertReporterName", "#newsEndContents > div.reporter_area > div.reporter._type_journalist > div.reporter_profile > div > div.profile_info > a > div.name");
				
				HashMap<String, String> crawlingData = htmlCrawlingUtil.getDataInHtml(detailResponseBody, targetTags);
				for (String targetTagTitle : crawlingData.keySet()) {
					System.out.println(targetTagTitle + " : " + crawlingData.get(targetTagTitle)); 
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
