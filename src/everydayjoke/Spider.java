package everydayjoke;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

@SuppressWarnings("deprecation")
public class Spider {
	public Spider(){}
	
	public boolean getJoke(String url){
		//1.create a request type (get or post)
		HttpUriRequest request = new HttpGet(url);
		
		//2.create a brower client
		HttpClient client = new DefaultHttpClient();
		
		try {
			//brower carry out get reponse
			HttpResponse response = client.execute(request);
			//get state code
			System.out.println(response.getStatusLine());
			//get reponse emtity --> inputstream
			HttpEntity emtity = response.getEntity();
			InputStream in = emtity.getContent();
			//inputstream tranfer to String
			BufferedReader br = new BufferedReader(new InputStreamReader(in,"gbk"));
			String content = "";
			String temp = "";
			while ((temp = br.readLine()) != null){
				content += temp;
            }
			System.out.println(content);
			//use regex match we want(title,content)
			String titleRGX = "article-summary.*?article-title.*?target.*?>(.*?)</a>.*?summary-text\">(.*?)</div>";
			Pattern pattern = Pattern.compile(titleRGX); 
			Matcher matcher = pattern.matcher(content);
			
			while(matcher.find()){
				String title = matcher.group(1);
				String article = matcher.group(2);
				System.out.println(title);
				article = article.replace("<p>", "");
				article = article.replace("</p>", "");
				System.out.println(article);
				System.out.println("------------");
				
				Uniq tUniq = new Uniq();
				if(tUniq.isRepitition(title+","+content)){
					System.out.println("笑话重复，不存进数据库！");
					continue;
				}
				
				if(!insertDB(title,article)){
					System.out.println(title+" 笑话保存进数据库失败！");
					return false;
				}

			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	// insert titie,content into database
	private boolean insertDB(String title,String content){
		ExecSQL tExecSQL = new ExecSQL();
		boolean res = tExecSQL.executeInsert("insert into article(title,content,hassend) values('"+title+"','"+content+"','0')");
		if(!res){
			return false;
		}
		return true;
	}
	
	


}
