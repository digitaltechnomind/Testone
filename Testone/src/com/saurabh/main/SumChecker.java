package com.saurabh.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SumChecker {
	
	Map<Integer,String> sumMap;
	
	public SumChecker(){
		sumMap=new HashMap<Integer,String>();
	}
	
	public static void main(String[] args) {
		
		int[] numberList=new int[]{3,4,7,1,2,9,8};
		SumChecker sumChecker=new SumChecker();
		System.out.print(sumChecker.displayIndices(numberList));
	
	}

	private void createIndexMap(int[] numberList){
		int sum=0;
		
		for(int index=0;index<numberList.length;index++)
		{
			for(int position=index+1;position<numberList.length;position++)
			{
				sum=numberList[index]+numberList[position];
				if(sumMap.get(sum)!=null){
					sumMap.put(sum,sumMap.get(sum)+":"+index+","+position );
				}else{
				sumMap.put(sum,index+","+position );
				}
			}
		}
		
	}
	public String displayIndices(int[] numberList){
		
		createIndexMap(numberList);
		String listOfIndices="";
		Set<Integer> keyset=sumMap.keySet();
		for(int mapoint:keyset)
		{
			if(sumMap.get(mapoint)!=null&&(sumMap.get(mapoint)).contains(":"))
			{
				String[] printcoordinate=sumMap.get(mapoint).split(":");
				for(int c=0;c<printcoordinate.length;c++)
				{
					for(int j=c+1;j<printcoordinate.length;j++)
					{
						listOfIndices=listOfIndices+"("+printcoordinate[c]+","+printcoordinate[j]+")";
					}
						
				}
				
			}
		}
		
		return listOfIndices;
		
	}
}
