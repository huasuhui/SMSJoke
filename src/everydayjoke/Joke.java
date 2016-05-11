package everydayjoke;

import java.util.HashMap;
import java.util.Map;

public class Joke {
	
	private String url = "http://api.laifudao.com/open/xiaohua.json";
	private Map<String,String> resultMap = null;
	public Joke(){}
	
	/**
	 * get All webContent
	 * */
	private String fromWebGetJoke(){
		DownloadPage mDownloadPage = new DownloadPage();
		String content = mDownloadPage.getContentFormUrl("http://api.laifudao.com/open/xiaohua.json");
		resultMap = format(content);
		System.out.println(resultMap.toString());
		return null;
	}
	
	/**
	 * format content --->Map
	 * */
	private HashMap<String,String> format(String content){
		String[] jokes = content.split("},{");
		for(String joke : jokes){
			
		}
		
		return null;
	}
	
	public static void mian(String[] args){
		Joke mJoke = new Joke();
		mJoke.fromWebGetJoke();
	}
}

