package com.center.buuluu.common.exception;

public enum ExceptionCodeStatusEnum{
	
	//-------system status
	SUCCESS_STATUS (1), 
	FAILED_STATUS (0),
	
	//-------system error
	ILLEGAL_ARGUMENT(-2),
	JOSN_EXCEPTION(-3),
	FILTER_EXCEPTION (-4),
	JSON_TO_MAP_EXCEPTION(-5);
	//--------------user error
	
    
	// 定义私有变量

     private int nCode ;
     // 构造函数，枚举类型只能为私有

     private ExceptionCodeStatusEnum(int _nCode) {

         this . nCode = _nCode;

     }
     
     @Override

     public String toString() {

         return String.valueOf ( this . nCode );

     }
     
     public Integer toInt() {

         return this . nCode;

     }
}
