package vn.DuyHao.configs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectSQL {
	private final String serverName = "DUYHAO\\DUYHAO";
	private final String dbName = "ltwchieuthu4";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "Ahao25092004";

	public Connection getConnection() {
		Connection conn = null;

		try {
			String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + " ;databaseName="
					+ dbName;

			if (instance == null || instance.trim().isEmpty())
				url = "jdbc:sqlserver://" + serverName + " ;databaseName="
						+ dbName;
			conn = DriverManager.getConnection(url, userID, password);

			if (conn != null) {
//				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
//				System.out.println("Driver name:" + dm.getDriverName());
//				System.out.println("Driver version: " + dm.getDriverVersion());
//				System.out.println("Product name: " + dm.getDatabaseProductName());
//				System.out.println("Product version: " + dm.getDatabaseProductVersion());

				return conn;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
//		} finally {
//			try {
//				if (conn != null && !conn.isClosed()) {
//					conn.close();
//				}
//			} catch (SQLException ex) {
//				ex.printStackTrace();
//			}
		}
		return null;
	}
	
	public static void main(String[] args) {
        DBConnectSQL dbConnect = new DBConnectSQL();
        Connection conn = dbConnect.getConnection();
        
        if (conn != null) {
            System.out.println("Kết nối tới cơ sở dữ liệu thành công!");
        } else {
            System.out.println("Kết nối tới cơ sở dữ liệu thất bại.");
        }
    }
}
