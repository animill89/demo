package common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import news.bean.User;

public class DbUtil {

	private Connection conn;

	public DbUtil(){
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/demo";
			String username = "root";
			String password = "123456";
			Class.forName("com.mysql.jdbc.Driver");
			//mysql 8.0及以上版本用这个驱动
			//Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	public Connection initConnection() {
		return conn;
	}

	/**
	 * 关闭连接
	 * @param conn
	 */
	public void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭statement对象
	 * @param stmt
	 */
	public void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询
	 * @param sql
	 * @param o
	 * @return
	 */
	public List<Map<String, String>> genericQuery(String sql, Object[] o){
		List<Map<String, String>> list = null;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			if (o != null) {
				for(int i = 1; i <= o.length; i++) {
					ps.setObject(i, o[i-1]);
				}
			}
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			list = new ArrayList<Map<String,String>>();
			while(rs.next()) {
				Map<String, String> m = new HashMap<String, String>();
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					m.put(rsmd.getColumnName(i), rs.getString(i));
				}
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(ps);
		}
		return list;
	}

	/**
	 * 执行查询
	 * @param <T>
	 * @param sql
	 * @param o
	 * @param c
	 * @return
	 */
	public  <T> List<T> genericQuery(String sql, Object[] o, Class<T> c){
		List<T> list = null;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			if (o != null) {
				for(int i = 1; i <= o.length; i++) {
					ps.setObject(i, o[i-1]);
				}
			}
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			list = new ArrayList<T>();
			while(rs.next()) {
				T t = c.getDeclaredConstructor().newInstance();
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					//                    String columnName = rsmd.getColumnName(i);
					String columnName = rsmd.getColumnLabel(i);

					String property = "";
					boolean upcase = false;
					for(int k = 0; k < columnName.length(); k++) {
						if (columnName.charAt(k) == '_') {
							upcase = true;
							continue;
						}
						if (upcase) {
							property += (""+columnName.charAt(k)).toUpperCase();
						}else {
							property += columnName.charAt(k);
						}
						upcase = false;
					}
					String methodName = "set" + property.substring(0, 1).toUpperCase()
							+ property.substring(1, property.length());
					Method method = c.getDeclaredMethod(methodName, 
							c.getDeclaredField(property).getType());
					Object obj = rs.getObject(i);
					method.invoke(t, obj);
				}
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} finally {
			
			closeStatement(ps);
		}
		return list;
	}

	/**
	 * 执行增删改
	 * @param sql 
	 * @param o -参数
	 * @return
	 */
	public int genericDML(String sql, Object[] o) {
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			if (o != null) {
				for(int i = 1; i <= o.length; i++) {
					ps.setObject(i, o[i-1]);
				}
			}
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(ps);
		}
		return result;
	}

	public Connection getConn(){
		return  conn;
	}

	public static void main(String[] args) {
		//创建工具类
		DbUtil dbUtil = new DbUtil();
		//查询案例
		String sql = "select * from user where name=? and password=? and role=?";
		Object[] objects = new Object[]{"lily","9","1"};
		List<User> list = dbUtil.genericQuery(sql, objects, User.class);
		if (list != null) {
			for(User user: list) {
				System.out.println(user.getId() + ", " + user.getName());
			}
		}
		//新增案例（删除、修改类似）
//
//		String sql = "insert into user(name,password) values(?,?)";
//		Object[] objects = new Object[]{"lily","123"};
//		int result  = dbUtil.genericDML(sql, objects);
		
		dbUtil.closeConnection(dbUtil.getConn());
	}

}