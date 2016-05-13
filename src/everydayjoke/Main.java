package everydayjoke;

public class Main {

	public static void main(String[] args){
		
//		// SMS test success
//		SMSSend mSMSSend = new SMSSend();
//		mSMSSend.taobaosend("花苏徽","15601581985","笑话实验","这是一则笑话！成功了哦！");

		// joke get test
		String jokeURL = "http://xiaohua.zol.com.cn/baoxiao/";
		Spider spider = new Spider();
		if(!spider.getJoke(jokeURL)){
			System.out.println("抓取网页信息失败并存储失败！");
		}
	}

}
