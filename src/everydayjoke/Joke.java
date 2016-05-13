package everydayjoke;

import java.util.ArrayList;

public class Joke {
	private ExecSQL tExecSQL = new ExecSQL();
	Joke(){}
	
	private void start(){
		// joke get test
		String jokeURL = "http://xiaohua.zol.com.cn/baoxiao/";
		Spider spider = new Spider();
		if(!spider.getJoke(jokeURL)){
			System.out.println("ץȡ��ҳ��Ϣʧ�ܲ��洢ʧ�ܣ�");
		}
		if(!startSendJoke()){
			System.out.println("���ս��ʧ�ܣ�");
		}
	}
	
	
	/**
	 * ����SMSSend���Ͷ���
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
		//Ц������һ���Ժ��ٷ�����
		if(!updateHasSend(articleid)){
			System.out.println(articleid+"���Ѿ����ͣ�����д�����ݿ�ʧ�ܣ�");
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

