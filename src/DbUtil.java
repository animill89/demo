import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

import news.bean.User;


//import cs.bean.Student;

public class DbUtil {

	private Connection conn;

	public DbUtil(){
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/news";
			String username = "root";
			String password = "123456";
			Class.forName("com.mysql.jdbc.Driver");
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
	 * �ر�����
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
	 * �ر�statement����
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
	 * ִ�в�ѯ
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
	 * ִ����ɾ��
	 * @param sql 
	 * @param o -����
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
		//����������
		DbUtil dbUtil = new DbUtil();
		//��ѯ����
//		List<User> list = dbUtil.genericQuery("select id, name from user where id=? and name=?", new Object[]{1,"lily"}, User.class);
//		if (list != null) {
//			for(User user: list) {
//				System.out.println(user.getId() + ", " + user.getName());
//			}
//		}
		//����������ɾ�����޸����ƣ�
		Object[] objects = new Object[]{"lily"};
		int result  = dbUtil.genericDML("insert into user(name) values(?)", objects);
		
		dbUtil.closeConnection(dbUtil.getConn());
	}

}
