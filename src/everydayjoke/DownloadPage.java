package everydayjoke;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
  
public class DownloadPage  
{  
	DownloadPage(){}
   /**  
    * 
    *   
    * @param url  
    * @return  
    */
   public static String getContentFormUrl(String url)  
   {  
       
       @SuppressWarnings("deprecation")
       HttpClient client = new DefaultHttpClient();  
       HttpGet getHttp = new HttpGet(url);  
       String content = null;  
  
       HttpResponse response;  
       try
       {  
           
           response = client.execute(getHttp);  
           HttpEntity entity = response.getEntity();  
  
//           VisitedUrlQueue.addElem(url);  
  
           if (entity != null)  
           {  
               
               content = EntityUtils.toString(entity,"UTF-8");  
               
  
               
//               if (FunctionUtils.isCreateFile(url)  
//                       && FunctionUtils.isHasGoalContent(content) != -1)  
//               {  
//                   FunctionUtils.createFile(FunctionUtils  
//                           .getGoalContent(content), url);  
//               }  
           }  
  
       } catch (ClientProtocolException e)  
       {  
           e.printStackTrace();  
       } catch (IOException e)  
       {  
           e.printStackTrace();  
       } finally  
       {  
           client.getConnectionManager().shutdown();  
       }  
           
       return content;  
   }  
   
//   public static void main(String[] args){
//	   DownloadPage mDownloadPage = new DownloadPage();
//	   String content = mDownloadPage.getContentFormUrl("http://api.laifudao.com/open/xiaohua.json");
//	   System.out.println(content);
//   }
  
}
