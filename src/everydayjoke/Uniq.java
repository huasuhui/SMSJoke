package everydayjoke;

import java.util.ArrayList;
import java.util.HashSet;

public class Uniq {
	
	private ArrayList<String> splitREG = null;
	
	Uniq(){
		initSplitReg();
	}
	
	/**
	 * 初始化分割规则
	 * */
	private void initSplitReg(){
		splitREG.add(",");
		splitREG.add(".");
		splitREG.add(",");
		splitREG.add("。");
	}
	
	public boolean isRepitition(String str){
		
		isHasJoke(str);
		return true;
	}
	
	/**
	 * 判断一个笑话是否已经存在于数据库中
	 * true 表示存在重复  false 表示不存在
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
	 * 将制定的字符串按分割规则分割成数个字符串
	 * @param String
	 * @return HashSet
	 * */
	private HashSet<String> splitStr(String str){
		HashSet<String> resulthashset = new HashSet<String>();
		//先分割一次，给for循环中使用
		String[] tmpStr = str.split(splitREG.get(0));
		for(int i=0;i<=tmpStr.length;i++)
			resulthashset.add(tmpStr[i]);
		
		//三段for循环，看着就不爽，得优化
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
			if(!splitFlag){//如果一个循环下来没有分割一次，表明无法再分割下去了
				break;
			}
			resulthashset = tmphashset;
			splitFlag = false;
		}
		
		return resulthashset;
	}

}
