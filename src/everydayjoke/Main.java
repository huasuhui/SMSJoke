package everydayjoke;

public class Main {

	public static void main(String[] args){
		
		// SMS test success
		SMSSend mSMSSend = new SMSSend();
		mSMSSend.taobaosend("花苏徽","15601581985","笑话实验","这是一则笑话！成功了哦！");

		
	}

}
