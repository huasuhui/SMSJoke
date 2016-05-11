package everydayjoke;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SMSSend {
	private String url = "http://gw.api.taobao.com/router/rest";
	private String appkey = "23349491";
	private String secret = "4418506347c5ad242938dc73477221d1";

	public SMSSend(){}
	
	/**
	 *taobao dayu SMS api
	 * */
	
	public void taobaosend(String content){
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName("活动验证");
		req.setSmsParamString("{\"code\":\"huasuhui\",\"product\":\"suifengJoke\",\"item\":\""+content+"\"}");
		req.setRecNum("15601581985");
		req.setSmsTemplateCode("SMS_7865119");
		AlibabaAliqinFcSmsNumSendResponse rsp;
		
		String result = "";
		
		try {
			rsp = client.execute(req);
			result = rsp.getBody();
		} catch (ApiException e) {
			e.printStackTrace();
		}
		System.out.println(result);
	}
	
}
