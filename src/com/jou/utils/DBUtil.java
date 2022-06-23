package com.jou.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class DBUtil {
	private static Logger logger = Logger.getLogger(DBUtil.class);
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	public static Connection getConnection() throws Exception {

		try {
			Connection conn = tl.get();
			if (conn == null) {
				// conn为空就创建一个新的conn
				conn = DBDataSource.getConnectionC3P0();
				// 将conn存入到当前线程中
				tl.set(conn);
			}
			return conn;
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new Exception(e);

		}
	}

	/**
	 * 获取单条记录，并以对象形式返回，sql中没有占位符参数 select * from table where id = 1
	 * 
	 * @param sql
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <T> T getObject(String sql, Class<T> cls) throws Exception {
		return getObject(sql, null, cls);
	}

	/**
	 * 获取单条记录，并以对象形式返回，sql中含有占位符参数 select * from table where id = ?
	 * 
	 * @param sql
	 * @param param
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <T> T getObject(String sql, List<Object> paramList, Class<T> cls) throws Exception {
		Connection conn = getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		T singleObject = null;
		int index = 1;

		try {
			pst = conn.prepareStatement(sql);
			if (pst == null) {
				return null;
			}
			setPreparedStatementParamByType(pst,paramList);
			rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				singleObject = cls.newInstance();
				for (int i = 0; i < columnCount; i++) {
					// 获取的是原始名
					// String columnName = rsmd.getColumnName(i + 1);
					// 获取的是别名
					String columnName = rsmd.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(columnName);
					// 如果数据库字段带有下划线，比如user_id,则需要转为userId;
					columnName = StringUtil.lineToHump(columnName);
					Field field = cls.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(singleObject, columnValue);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw new Exception(e);
		} finally {
			close(conn, pst);
		}
		return singleObject;
	}

	/**
	 * 获取单条记录，并以Map形式返回，sql中含有占位符参数
	 * 
	 * @param sql：查询语句
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getObjectMap(String sql) throws Exception {
		return getObjectMap(sql, null);
	}

	/**
	 * 获取单条记录，并以Map形式返回，sql中含有占位符参数
	 * 
	 * @param sql：查询语句
	 * @param paramList：参数
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getObjectMap(String sql, List<Object> paramList) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			logger.info("parameter is valid!");
			return null;
		}

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Map map = new HashMap<String, Object>();
		try {
			conn = getConnection();
			pst = conn.prepareStatement(sql);
			if (pst == null) {
				return null;
			}
			setPreparedStatementParamByType(pst, paramList);
			rs = pst.executeQuery();
			List list = getQueryMapList(rs);
			if (list.isEmpty()) {
				return null;
			}
			map = (HashMap) list.get(0);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new Exception(e);
		} finally {
			close(conn, pst, rs);
		}
		return map;
	}

	/**
	 * 查询记录，返回对象列表，sql中没有占位符参数 select * table where name like '%张三%';
	 * 
	 * @param sql
	 *            查询语句
	 * @param cls
	 *            返回对象的类型
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> getQueryList(String sql, Class<T> cls) throws Exception {
		return getQueryList(sql, null, cls);

	}

	/**
	 * 查询记录，返回对象列表，sql中含有占位符参数 select* from table where name like '%?%'
	 * 
	 * @param sql
	 *            查询语句
	 * @param paras
	 *            查询参数
	 * @param cls
	 *            返回对象的类型
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> getQueryList(String sql, List<Object> paramList, Class<T> cls) throws Exception {
		Connection conn = getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		T singleObject = null;
		int index = 1;
		List<T> list = new ArrayList<T>();
		try {
			pst = conn.prepareStatement(sql);
			if (pst == null) {
				return null;
			}
			setPreparedStatementParamByType(pst, paramList);
			rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				singleObject = cls.newInstance();
				for (int i = 0; i < columnCount; i++) {
					// 获取的是原始名
					// String columnName = rsmd.getColumnName(i + 1);
					// 获取的是别名
					String columnName = rsmd.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(columnName);
					// 如果数据库字段带有下划线，比如user_id,则需要转为userId;
					columnName = StringUtil.lineToHump(columnName);
					Field field = cls.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(singleObject, columnValue);
				}
				list.add(singleObject);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new Exception(e);
		} finally {
			close(conn, pst, rs);
		}
		return list;
	}

	/**
	 * 查询记录，返回Map数据列表，sql中无占位符参数 select * table where name like '%张三%';
	 * 
	 * @param sql：查询语句
	 * @return
	 */
	public static List<Map<String, Object>> getQueryMapList(String sql) throws Exception {
		return getQueryMapList(sql, null);
	}

	/**
	 * 查询记录，返回Map数据列表，sql有占位符参数 select * table where name like '%?%';
	 * 
	 * @param sql
	 *            查询
	 * @param paramList
	 *            占位符参数
	 * @return
	 */
	public static List<Map<String, Object>> getQueryMapList(String sql, List<Object> paramList) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			logger.info("parameter is valid!");
			return null;
		}

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Map<String, Object>> queryList = null;
		try {
			conn = getConnection();
			pst = conn.prepareStatement(sql);
			if (pst == null) {
				return null;
			}
			setPreparedStatementParamByType(pst, paramList);
			rs = pst.executeQuery();
			queryList = getQueryMapList(rs);
		} catch (RuntimeException e) {
			logger.info(e.getMessage());
			throw new Exception(e);
		} finally {
			close(conn, pst, rs);
		}
		return queryList;
	}

	/**
	 * 该语句必须是 SQL INSERT、UPDATE 、DELETE 语句，无sql占位符，无参
	 * 
	 * @param sql
	 * 			查询语句
	 * @return
	 * 			返回影响行数
	 * @throws Exception
	 */
	public static int execute(String sql) throws Exception {
		return execute(sql, null);
	}

	/**
	 * 该语句必须是 SQL INSERT、UPDATE 、DELETE 语句 insert into table values(?,?,?,?)
	 * 
	 * @param sql
	 * 			查询sql
	 * @param paramList
	 * 			参数，与SQL语句中的占位符一
	 * @return 返回影响行数
	 * @throws Exception
	 */
	public static int execute(String sql, List<Object> paramList) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			logger.info("parameter is valid!");
		}

		Connection conn = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			conn = getConnection();
			pst = conn.prepareStatement(sql);
			if (pst == null) {
				return -1;
			}
			setPreparedStatementParamByType(pst, paramList);
			result = pst.executeUpdate();
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new Exception(e);
		} finally {
			close(conn, pst);
		}

		return result;
	}

	/**
	 * insert语句使用，返回新增数据的主键id
	 * 
	 * @param sql
	 *            查询语句
	 * @param paramList
	 *            参数
	 * @param falg
	 * @return 返回主键
	 * @throws Exception
	 */
	public static Object execute(String sql, List<Object> paramList, boolean falg) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			logger.info("parameter is valid!");
		}
		Connection conn = null;
		PreparedStatement pst = null;
		// 指定返回生成的主键
		Object id = null;
		try {
			conn = getConnection();
			// 设置返回主键
			pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (pst == null) {
				return -1;
			}
			// 如果使用静态SQL，则不需要动态插入参数
			setPreparedStatementParamByType(pst, paramList);
			pst.executeUpdate();
			// 检索由于执行此 Statement 对象而创建的所有自动生成的键
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getObject(1);
				System.out.println("数据主键：" + id);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new Exception(e);
		} finally {
			close(conn, pst);
		}
		return id;
	}

	/**
	 * mysql结果集转map数据类型
	 * 
	 * @param rs
	 *            结果集
	 * @return
	 * @throws Exception
	 */
	private static List<Map<String, Object>> getQueryMapList(ResultSet rs) throws Exception {
		if (rs == null) {
			return null;
		}
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for (int i = 0; i < columnCount; i++) {
				dataMap.put(rsMetaData.getColumnLabel(i + 1), rs.getObject(i + 1));
			}
			dataList.add(dataMap);
		}
		return dataList;
	}

	/**
	 * sql语句占位符参数赋值
	 * 
	 * @param pst
	 *            预编译对象
	 * @param paramList
	 *            查询参数
	 * @throws Exception
	 */
	private static void setPreparedStatementParam(PreparedStatement pst, List<Object> paramList) throws Exception {

		if (paramList != null && paramList.size() > 0) {
			pst.clearParameters();
			for (int i = 0; i < paramList.size(); i++) {
				pst.setObject(i + 1, paramList.get(i));
			}
		}
	}

	public static void setPreparedStatementParamByType(PreparedStatement pst, List<Object> paramList)
			throws SQLException {
		
		if (paramList != null && paramList.size() > 0) {
			int i=1;
			for (Object arg : paramList) {
				if (arg instanceof Date) {
					pst.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
				} else if (arg instanceof Integer) {
					pst.setInt(i++, (Integer) arg);
				} else if (arg instanceof Long) {
					pst.setLong(i++, (Long) arg);
				} else if (arg instanceof Double) {
					pst.setDouble(i++, (Double) arg);
				} else if (arg instanceof Float) {
					pst.setFloat(i++, (Float) arg);
				} else {
					pst.setString(i++, (String) arg);
				}
			}
		}
		

	}

	/**
	 * 开启失物
	 */
	public static void beginTranscation() throws Exception {
		Connection conn = tl.get();
		if (conn != null) {
			logger.info("事务已经开始！");
			throw new SQLException("事务已经开始！");
		}
		conn = DBDataSource.getConnectionC3P0();
		conn.setAutoCommit(false);
		tl.set(conn);
	}

	/**
	 * 结束事务
	 * 
	 * @throws SQLException
	 */
	public static void endTranscation() throws SQLException {
		Connection conn = tl.get();
		if (conn == null) {
			logger.info("当前没有事务！");
			throw new SQLException("当前没有事务！");
		}
		conn.commit();
	}

	/**
	 * 回滚
	 * 
	 * @throws SQLException
	 */
	public static void rollback() throws SQLException {
		Connection conn = tl.get();
		if (conn == null) {
			logger.info("当前没有事务,不能回滚！");
			throw new SQLException("当前没有事务,不能回滚！");
		}
		conn.rollback();
	}

	/*
	 * 释放资源
	 */
	public static void close(Connection conn, PreparedStatement pst) throws Exception {
		try {
			pst.close();
			conn.close();
			// 因为应用了线程池，不仅要关闭连接，关闭连接后还需要在线程池中移除这个连接
			tl.remove();
		} catch (SQLException e) {
			logger.info(e.getMessage());
			throw new Exception(e);
		}
	}

	// 关闭的通用方法
	private static void close(Connection conn, PreparedStatement pst, ResultSet set) throws Exception {
		try {
			if (set != null) {
				set.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
				// 因为应用了线程池，不仅要关闭连接，关闭连接后还需要在线程池中移除这个连接
				tl.remove();
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new Exception(e);
		}
	}

}