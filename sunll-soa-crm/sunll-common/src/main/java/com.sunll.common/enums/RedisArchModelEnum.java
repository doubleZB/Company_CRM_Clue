package com.sunll.common.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Description: redis 运行模式 
 * All Rights Reserved.
 */
public enum RedisArchModelEnum {
	
	SINGLE("single", "SINGLE"),
	MASTERSLAVE("ms", "MASTER-SLAVE"),
	SENTINEL("sentinel", "SENTINEL"),
	CLUSTER("cluster", "CLUSTER");
	
	private String model;
	private String desc;
	
	private RedisArchModelEnum(String model, String desc) {
		this.model = model;
		this.desc = desc;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static RedisArchModelEnum getRedisArchModelByModel(String model){
		if(StringUtils.isNotBlank(model)){
			for(RedisArchModelEnum item:RedisArchModelEnum.values()){
				if(item.getModel().equals(model)){
					return item;
				}
			}
		}
		return null;
	}

}
