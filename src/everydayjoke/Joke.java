package everydayjoke;

import java.util.ArrayList;

public class Joke {
	private ExecSQL tExecSQL = new ExecSQL();
	
	private int jokeNumLimit = 30;
	
	Joke(){}
	
	private void start(){
		// joke get test
		String jokeURL = "http://xiaohua.zol.com.cn/baoxiao/";
		Spider spider = new Spider();
		if(checkJokeLimit()){
			if(!spider.getJoke(jokeURL)){
				System.out.println("spider faile");
			}
		}
		
		if(!startSendJoke()){
			System.out.println("send faile");
		}
	}
	
	//query joke beyond limit 
	private boolean checkJokeLimit(){
		String jokenum = tExecSQL.getOneValue("select count(1) from article where hassend = '0'");
		return Integer.parseInt(jokenum) <= jokeNumLimit;
	}
	
	
	/**
	 * start send SMS
	 * */
	private boolean startSendJoke(){
		ArrayList<ArrayList<String>> phoneList = tExecSQL.executeQuery("select name,phone from phone");
		ArrayList<ArrayList<String>> jokeList = tExecSQL.executeQuery("select articleid,title,content from article");
		String articleid = jokeList.get(0).get(0);
		String title = jokeList.get(0).get(1);
		String content = jokeList.get(0).get(2);
		SMSSend mSMSSend = new SMSSend();
		for(int i=0;i<=phoneList.size();i++){
			String name = phoneList.get(i).get(0);
			String phone = phoneList.get(i).get(1);
			if(!mSMSSend.taobaosend(name, phone, title, content)){
				return false;
			}
		}
		// only send one time
		if(!updateHasSend(articleid)){
			System.out.println(articleid+"update faile");
			return false;
		}
		
		return true;
	}
	
	private boolean updateHasSend(String articleid){
		return "1".equals(tExecSQL.executeInsert("update article set hassend = '1' where articleid = '"+articleid+"'"));
	}
	
	
	
	public static void mian(String[] args){
		Joke mJoke = new Joke();
		mJoke.start();
	}
}

