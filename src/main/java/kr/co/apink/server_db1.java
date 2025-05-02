package kr.co.apink;

import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.stereotype.Repository;

@Repository("server_db1")
public class server_db1 {
	String dbinfo = "";
	String dburl = "";
	String dbuser = "";
	String dbpass = "";
	Connection con = null;
	
	//localhost Mysql
	public Connection dbinfo1() throws Exception{
		this.dbinfo = "com.mysql.cj.jdbc.Driver";
		this.dburl = "jdbc:mysql://172.30.1.22:53306/mrp";
		//this.dburl = "jdbc:mysql://localhost:3306/mrp";
		this.dbuser = "apink";
		this.dbpass = "apink1004";
		
		Class.forName(this.dbinfo);
		this.con = DriverManager.getConnection(this.dburl,this.dbuser,this.dbpass);
		
		return this.con;
	}
	//Cloud DB
	public Connection dbinfo2() throws Exception{
		this.dbinfo = "com.mysql.cj.jdbc.Driver";
		//this.dburl = "jdbc:mysql://172.30.1.22:13306/webapi";
		this.dburl = "jdbc:mysql://localhost:13306/webapi";
		this.dbuser = "webapi";
		this.dbpass = "b402402";
		
		Class.forName(this.dbinfo);
		this.con = DriverManager.getConnection(this.dburl,this.dbuser,this.dbpass);
		
		return this.con;
	}
	
	
	
	
}
