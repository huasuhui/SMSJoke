package everydayjoke;

public class Main {

	public static void main(String[] args){
		
//		// SMS test success
//		SMSSend mSMSSend = new SMSSend();
//		mSMSSend.taobaosend("���ջ�","15601581985","Ц��ʵ��","����һ��Ц�����ɹ���Ŷ��");

		// joke get test
		String jokeURL = "http://xiaohua.zol.com.cn/baoxiao/";
		Spider spider = new Spider();
		if(!spider.getJoke(jokeURL)){
			System.out.println("ץȡ��ҳ��Ϣʧ�ܲ��洢ʧ�ܣ�");
		}
	}

}
