package com.center.buuluu.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class SqliteUtil {
	
	public static Connection conn = null;

	public static void getConntion(String filePath,String dbName) {
		try {
			String driver_class = "org.sqlite.JDBC";
			String dbSource = "jdbc:sqlite:"+filePath+dbName;

			Class.forName(driver_class);
			 conn = DriverManager.getConnection(dbSource);  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getConntion(String dbName) {
		try {
			String driver_class = "org.sqlite.JDBC";
			String dbSource = "jdbc:sqlite:"+dbName;

			Class.forName(driver_class);
			 conn = DriverManager.getConnection(dbSource);  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) throws SQLException {
		getConntion("/c:/Users/DELL/Desktop/50k.db");
		  Statement stat = conn.createStatement();  
		  StringBuilder sql = new StringBuilder();
		  for (int i = 600; i >0; i--) {
			  sql.setLength(0);
			String d =DateUtil.format(DateUtil.getBeforeDate(new Date(), i));
			sql.append("insert into step_report(steps,date,hour,calories,kilometer,uuid) values(10000,'"+d+"',10,1000,1000,'SNOS01DFA457');");
			stat.execute(sql.toString());
		}
//          stat.executeUpdate("drop table if exists Branch;");
//           stat.executeUpdate("CREATE TABLE Branch (id INTEGER NOT NULL PRIMARY KEY, bocbranchid INTEGER NOT NULL," +
//           		" region_id INTEGER NOT NULL, " +
//           		"district_id INTEGER NOT NULL,  longtitude NUMERIC, latitude NUMERIC,  name_en TEXT,  name_zht TEXT, " +
//           		"name_zhs TEXT, address_en TEXT, address_zht TEXT, address_zhs TEXT, phone TEXT, fax TEXT, " +
//           		"officehour_en TEXT, officehour_zht TEXT, officehour_zhs TEXT, salesperson TEXT, salespersontel TEXT, display_order INTEGER, isbranch TEXT);");  
//           stat.executeUpdate("insert into Branch values(0, 0, 2, 8, 114.200563, 22.350947, 'Tsz Ching Shopping Centre ATM Services', '慈正商場', '慈正商场', 'ATM No.2, G/F, Tsz Ching Shopping Centre II, Tsz Ching Estate, Wong Tai Sin', '黃大仙慈雲山慈正商場(II)期地下 ATM No.2', '黄大仙慈云山慈正商场(II)期地下 ATM No.2', null, null, null, null, null, null, null, 0, 'A');"); // 插入数据 
//		  ResultSet re = stat.executeQuery("select * from  step_daily;");
//		  while (re.next()) {
//			System.out.println(re.getInt("id")); 
//			System.out.println(re.getInt("sleep_efficiency"));
//			System.out.println(re.getInt("turns"));
//			System.out.println(re.getFloat("sleep_hours"));
//		}
//		  re.close();
		  stat.close();
           conn.close();
	}
}
