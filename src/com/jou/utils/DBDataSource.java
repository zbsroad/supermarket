package com.jou.utils;

import java.sql.Connection;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库连接池
 * 
 */
public class DBDataSource {
	//数据库连接参数
	public static String driver;
	public static String url;
	public static String user;
	public static String pwd;
	public static int maxPoolSize;
	public static int minPoolSize;
	public static int initialPoolSize;
	public static int checkoutTimeout;

	private static ComboPooledDataSource cpDataSource = null;

	// 加载驱动
	static {
		try {
			// 读取配置文件，加载JDBC四大参数
			driver = PropertiesUtil.getValue("drivername");
			url = PropertiesUtil.getValue("url");
			user = PropertiesUtil.getValue("username");
			pwd = PropertiesUtil.getValue("password");

			maxPoolSize = Integer.parseInt(PropertiesUtil.getValue("maxPoolSize"));
			minPoolSize = Integer.parseInt(PropertiesUtil.getValue("minPoolSize"));
			initialPoolSize = Integer.parseInt(PropertiesUtil.getValue("initialPoolSize"));
			checkoutTimeout = Integer.parseInt(PropertiesUtil.getValue("checkoutTimeout"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**************** c3p0 数据库连接池 启动方法 ******************/
	private static void c3p0DataSource() throws Exception {
		cpDataSource = new ComboPooledDataSource();
		cpDataSource.setDriverClass(driver);
		cpDataSource.setJdbcUrl(url);
		cpDataSource.setUser(user);
		cpDataSource.setPassword(pwd);
		cpDataSource.setMaxPoolSize(maxPoolSize);
		cpDataSource.setMinPoolSize(minPoolSize);
		cpDataSource.setInitialPoolSize(initialPoolSize);
		cpDataSource.setCheckoutTimeout(checkoutTimeout);
	}

	/**
	 * c3p0数据库连接入
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnectionC3P0() throws Exception {
		if (cpDataSource == null) {
			c3p0DataSource();
		}
		Connection conn = null;
		if (cpDataSource != null) {
			conn = cpDataSource.getConnection();
		}
		return conn;
	}

}