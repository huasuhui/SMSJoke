package everydayjoke;

import java.util.HashSet;

public class Uniq {
	
	private String reg = "[����]";
	
	Uniq(){}

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
		String[] tmpStr = str.split(reg);
		for(int i=0;i<tmpStr.length;i++)
			resulthashset.add(tmpStr[i]);

		
		return resulthashset;
	}

}
