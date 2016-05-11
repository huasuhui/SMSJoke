package everydayjoke;

import jdbcUtils.ExecSQL;

public class Main {

	public static void main(String[] args){
		
		// SMS test success
//		SMSSend mSMSSend = new SMSSend();
//		mSMSSend.taobaosend();
		
		// joke get test
		Spider spider = new Spider();
		spider.getJoke("http://xiaohua.zol.com.cn/baoxiao/");
		
		//test exec
//		ExecSQL tExecSQL = new ExecSQL();
//		tExecSQL.executeQuery("select title,content from article");
	}

}
