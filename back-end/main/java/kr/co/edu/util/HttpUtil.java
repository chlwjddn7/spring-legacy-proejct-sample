package kr.co.edu.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * HTTP 통신 관련 유틸
 * 
 * @author 최정우
 * @since 2022.10.20
 */
public class HttpUtil {

	/**
	 * HTTP 통신 메서드
	 * 
	 * @author 최정우
	 * @since 2020.07.21
	 * @return http response body (text)
	 */
	public String httpRequest (String requestURL, Map<String, String> headers, Map<String, Object> params) {
		StringBuffer responseBody = new StringBuffer();
		
		try {
			//params to queryString -> GET방식일 때, requestURL에 추가 / POST방식일 때, HTTP Request Body에 추가
			String queryString = createUrlQuery(params, "UTF-8");
			if (queryString != null && queryString != "") {
				requestURL += "?";
				requestURL += createUrlQuery(params, "UTF-8");
			}
			
			//URL을 통한 통신 객체 생성
			URL url = new URL(requestURL);
			
			//URL객체를 통해 HTTP 통신 객체 생성
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("GET");//Method: GET
			httpURLConnection.setConnectTimeout(1000 * 60);//http통신 최대 커넥션 시간(1분)
			httpURLConnection.setReadTimeout(1000 * 30);//http통신 커넥션 이후, 데이터를 받아오는데 걸리는 최대 시간(30초)
			
			//HTTP Request 구문에 Header 세팅
			if (headers != null && headers.isEmpty() == false) {
				for (String headerKey : headers.keySet()) {
					httpURLConnection.setRequestProperty(headerKey, headers.get(headerKey));
				}
			}
			
			//HTTP Request 시작
			httpURLConnection.connect();
			//HTTP Request 응답 코드
			int responseCode = httpURLConnection.getResponseCode();
			
			if (responseCode == 200) {
				//HTTP Response를 받기 위한 Stream 객체 생성
				InputStream inputStreamForResponse = httpURLConnection.getInputStream();
				//Stream으로 들어오는 HTTP Response를 읽기 위한 객체 생성
				InputStreamReader inputStreamReaderForResponse = new InputStreamReader(inputStreamForResponse, "UTF-8");
				//읽어드린 Stream Data를 버퍼 객체에 담기
				BufferedReader bufferdReader = new BufferedReader(inputStreamReaderForResponse);
				
				//버퍼 객체에 한줄이 쌓일 때 마다, responseBody에 넣기
				String responseBodyLine = "";
				while ((responseBodyLine = bufferdReader.readLine()) != null) {
					responseBody.append(responseBodyLine);
				}
				
				//버퍼 객체에서 더 이상 읽어드릴 Data가 없어서 Null을 반환하여, while문이 종료 되고 난 이후, Input Stream 객체 소멸시키기
				//inputStreamForResponse.close();
				//inputStreamReaderForResponse.close();
				bufferdReader.close();
			} else {
				System.out.print("HTTP Request Error Code : " + responseCode);
			}
			
			
			//HTTP Request 종료
			httpURLConnection.disconnect();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
		
		return responseBody.toString();
	}
	
	
	
	
	/**
	 * 파라메터 데이터를 HTTP통신을 위한 문자열로 변환시켜주는 메서드
	 * 
	 * @author 최정우
	 * @since 2020.07.21
	 * @return URL QueryString
	 */
	public String createUrlQuery (Map<String, Object> params, String encoding) {
		if (params == null || params.isEmpty() == true) {
			return "";
		} else {
			StringBuilder query = new StringBuilder();
		    for(Map.Entry<String,Object> param : params.entrySet()) {
		        try {
		        	if(query.length() > 0) {
		        		query.append('&');
		        	}
		        	
		        	if (encoding == null) {
		        		query.append(param.getKey());
			        	query.append('=');
			        	query.append(param.getValue());
		        	} else {
		        		query.append(URLEncoder.encode(param.getKey(), encoding));
			        	query.append('=');
			        	query.append(URLEncoder.encode(String.valueOf(param.getValue()), encoding));
		        	}
		        	
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    return query.toString();
		}
	}
}
