package com.cz.mts.frame.task;

import java.util.List;

import com.cz.mts.frame.util.LuceneUtils;

public class LuceneTask implements Runnable {
	public final static String deleteDocument = "delete";
	public final static String updateDocument = "update";
	public final static String saveDocument = "save";

	public Object entity;

	public String oper;
	
	@SuppressWarnings("rawtypes")
	public Class clazz;
	

	public LuceneTask() {

	}

	public LuceneTask(Object entity, String oper) {
		this.oper = oper;
		this.entity = entity;
	}
	//删除专用
	@SuppressWarnings("rawtypes")
	public LuceneTask(Object id, Class clazz) {
		this.entity = id;
		this.clazz = clazz;
		this.oper="delete";
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void run() {
		try {
			if (deleteDocument.equals(oper)) {
				if(entity instanceof List){
					LuceneUtils.deleteListDocument((List)entity,clazz);
				}else{
					LuceneUtils.deleteDocument(entity,clazz);
				}
				
			}else if (updateDocument.equals(oper))  {
				if(entity instanceof List){
					 LuceneUtils.updateListDocument((List)entity);
				}else{
				   LuceneUtils.updateDocument(entity);
				}
			}else if (saveDocument.equals(oper))  {
				if(entity instanceof List){
					LuceneUtils.saveListDocument((List)entity);
				}else{
					LuceneUtils.saveDocument(entity);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
