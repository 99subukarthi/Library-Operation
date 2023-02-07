package com.my.DButil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import  static com.my.DBConstant.DBCon.*;

public class DBConnection {
static {
	try {
		Class.forName("com.mysql.cj.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static Connection getConnection() throws Exception {
	Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
	con.close();
	return con;
}
}
