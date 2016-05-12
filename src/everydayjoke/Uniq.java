package everydayjoke;

import java.util.ArrayList;
import java.util.HashSet;

public class Uniq {
	
	private ArrayList<String> splitREG = null;
	
	Uniq(){
		initSplitReg();
	}
	
	/**
	 * ��ʼ���ָ����
	 * */
	private void initSplitReg(){
		splitREG.add(",");
		splitREG.add(".");
		splitREG.add(",");
		splitREG.add("��");
	}
	
	public boolean isRepitition(String str){
		
		isHasJoke(str);
		return true;
	}
	
	/**
	 * �ж�һ��Ц���Ƿ��Ѿ����������ݿ���
	 * true ��ʾ�����ظ�  false ��ʾ������
	 * */
	private boolean isHasJoke(String str){
		HashSet<String> hashset = splitStr(str);
		boolean isHasJokeFlog = false;
		for(String strjoke : hashset){
			ExecSQL tExecSQL = new ExecSQL();
			String result = tExecSQL.getOneValue("select 1 from article where article like '"+strjoke+"' or title like '"+strjoke+"'");
			if("1".equals(result)){
				isHasJokeFlog = true;
				break;
			}
		}
		return isHasJokeFlog;
	}
	
	/**
	 * ���ƶ����ַ������ָ����ָ�������ַ���
	 * @param String
	 * @return HashSet
	 * */
	private HashSet<String> splitStr(String str){
		HashSet<String> resulthashset = new HashSet<String>();
		//�ȷָ�һ�Σ���forѭ����ʹ��
		String[] tmpStr = str.split(splitREG.get(0));
		for(int i=0;i<=tmpStr.length;i++)
			resulthashset.add(tmpStr[i]);
		
		//����forѭ�������žͲ�ˬ�����Ż�
		boolean splitFlag = true;;
		for(int x=1;x<=splitREG.size();x++){
			HashSet<String> tmphashset = new HashSet<String>();
			for(String strset : resulthashset){
				String[] tmp = 	strset.split(splitREG.get(x)); 
				if(splitFlag){
					for(int i=0;i<=tmp.length;i++){
						tmphashset.add(tmpStr[i]);
						splitFlag = true;
					}
				}
			}
			if(!splitFlag){//���һ��ѭ������û�зָ�һ�Σ������޷��ٷָ���ȥ��
				break;
			}
			resulthashset = tmphashset;
			splitFlag = false;
		}
		
		return resulthashset;
	}

}
