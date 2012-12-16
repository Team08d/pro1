package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import org.apache.commons.dbutils.DbUtils;

/**
 * Servlet implementation class SetupDatabase
 */
public class SetupDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String db_path = "jdbc:hsqldb:file:${user.home}/i377/Team08d/db;shutdown=true"; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Loading data...");

		try {
			createTables();
			response.getWriter().println("Success!");
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void createTables() throws Exception {	
		Connection conn = DriverManager.getConnection(db_path);

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			// RIIGI_ADMIN_YKSUSE_LIIK - ainult kasutatavad väljad
			stmt.executeUpdate("CREATE TABLE riigi_admin_yksuse_liik (riigi_admin_yksuse_liik_id IDENTITY," +
																	 "kood VARCHAR(10) NOT NULL," +
																	 "nimetus VARCHAR(100) NOT NULL," +
																	 "kommentaar LONGVARCHAR NOT NULL," +
																	 "alates DATE NOT NULL," +
																	 "kuni DATE NOT NULL," +
																	 "avatud DATE NOT NULL," +
																	 "muudetud DATE NOT NULL)");
			// LISAME NAIDISANDMED
			/*stmt.executeUpdate("INSERT INTO riigi_admin_yksuse_liik VALUES (1, 'R', 'Riik', 'test', '2012-01-01', '2012-12-31', '2012-12-16', '2012-12-16')");
			stmt.executeUpdate("INSERT INTO riigi_admin_yksuse_liik VALUES (2, 'M', 'Maakond', 'test', '2012-01-01', '2012-12-31', '2012-12-16', '2012-12-16')");
			stmt.executeUpdate("INSERT INTO riigi_admin_yksuse_liik VALUES (3, 'V', 'Vald', 'test', '2012-01-01', '2012-12-31', '2012-12-16', '2012-12-16')");
			stmt.executeUpdate("INSERT INTO riigi_admin_yksuse_liik VALUES (4, 'K', 'Küla', 'test', '2012-01-01', '2012-12-31', '2012-12-16', '2012-12-16')");*/

			// VOIMALIK_ALLUVUS - ainult kasutatavad väljad
			stmt.executeUpdate("CREATE TABLE voimalik_alluvus (voimalik_alluvus_id IDENTITY," +
															  "riigi_admin_yksuse_liik_id INTEGER NOT NULL," +
															  "voimalik_alluv_liik_id INTEGER NOT NULL," +
															  "kommentaar LONGVARCHAR NOT NULL," +
															  "avatud DATE NOT NULL," +
															  "muudetud DATE NOT NULL," +
															  "FOREIGN KEY (riigi_admin_yksuse_liik_id, voimalik_alluv_liik_id)" +
															  "REFERENCES riigi_admin_yksuse_liik(riigi_admin_yksuse_liik_id))");
			// LISAME NAIDISANDMED
			/*stmt.executeUpdate("INSERT INTO voimalik_alluvus VALUES (1, 1, 2, 'test', '2012-12-16', '2012-12-16')");
			stmt.executeUpdate("INSERT INTO voimalik_alluvus VALUES (2, 2, 3, 'test', '2012-12-16', '2012-12-16')");
			stmt.executeUpdate("INSERT INTO voimalik_alluvus VALUES (3, 3, 4, 'test', '2012-12-16', '2012-12-16')");*/
			
			// RIIGI_ADMIN_YKSUS - ainult kasutatavad väljad
			stmt.executeUpdate("CREATE TABLE riigi_admin_yksus (riigi_admin_yksus_id IDENTITY," +
															   "kood VARCHAR(20) NOT NULL," +
															   "niemtus VARCHAR(100) NOT NULL," +
															   "kommentaar LONGVARCHAR NOT NULL," +
															   "alates DATE NOT NULL," +
															   "kuni DATE NOT NULL," +
															   "avatud DATE NOT NULL," +
															   "muudetud DATE NOT NULL," +
															   "riigi_admin_yksuse_liik_id INTEGER NOT NULL," +
															   "FOREIGN KEY (riigi_admin_yksuse_liik_id)" +
															   "REFERENCES riigi_admin_yksuse_liik(riigi_admin_yksuse_liik_id))");
			// LISAME NAIDISANDMED
			/*stmt.executeUpdate("INSERT INTO riigi_admin_yksus VALUES (1, 'EV', 'Eesti Vabariik', 'test', '2012-01-01', '2012-12-31', '2012-12-16', '2012-12-16', 1)");
			stmt.executeUpdate("INSERT INTO riigi_admin_yksus VALUES (2, 'HARJU', 'Harjumaa', 'test', '2012-01-01', '2012-12-31', '2012-12-16', '2012-12-16', 2)");
			stmt.executeUpdate("INSERT INTO riigi_admin_yksus VALUES (3, 'SAARE', 'Saaremaa', 'test', '2012-01-01', '2012-12-31', '2012-12-16', '2012-12-16', 2)");
			stmt.executeUpdate("INSERT INTO riigi_admin_yksus VALUES (4, 'MUHU', 'Muhu vald', 'test', '2012-01-01', '2012-12-31', '2012-12-16', '2012-12-16', 3)");
			stmt.executeUpdate("INSERT INTO riigi_admin_yksus VALUES (5, 'RAE', 'Rae vald', 'test', '2012-01-01', '2012-12-31', '2012-12-16', '2012-12-16', 3)");
			stmt.executeUpdate("INSERT INTO riigi_admin_yksus VALUES (6, 'LIIVA', 'Liiva küla', 'test', '2012-01-01', '2012-12-31', '2012-12-16', '2012-12-16', 4)");
			stmt.executeUpdate("INSERT INTO riigi_admin_yksus VALUES (7, 'PEETRI', 'Peetri küla', 'test', '2012-01-01', '2012-12-31', '2012-12-16', '2012-12-16', 4)");*/
			
			// ADMIN_ALLUVUS - ainult kasutatavad väljad
			stmt.executeUpdate("CREATE TABLE admin_alluvus (admin_alluvus_id IDENTITY," +
														   "ylemus_yksus_id INTEGER NOT NULL," +
														   "alluv_yksus_id INTEGER NOT NULL," +
														   "alates DATE NOT NULL," +
														   "kuni DATE NOT NULL," +
														   "kommentaar LONGVARCHAR NOT NULL," +
														   "avatud DATE NOT NULL," +
														   "muudetud DATE NOT NULL," +
														   "FOREIGN KEY (ylemus_yksus_id, alluv_yksus_id)" +
														   "REFERENCES riigi_admin_yksus(riigi_admin_yksus_id))");
			// LISAME NAIDISANDMED
			/*stmt.executeUpdate("INSERT INTO admin_alluvus VALUES (1, 1, 2, '2012-01-01', '2012-12-31', 'test', '2012-12-16', '2012-12-16')");
			stmt.executeUpdate("INSERT INTO admin_alluvus VALUES (2, 1, 3, '2012-01-01', '2012-12-31', 'test', '2012-12-16', '2012-12-16')");
			stmt.executeUpdate("INSERT INTO admin_alluvus VALUES (3, 2, 5, '2012-01-01', '2012-12-31', 'test', '2012-12-16', '2012-12-16')");
			stmt.executeUpdate("INSERT INTO admin_alluvus VALUES (4, 3, 4, '2012-01-01', '2012-12-31', 'test', '2012-12-16', '2012-12-16')");
			stmt.executeUpdate("INSERT INTO admin_alluvus VALUES (5, 4, 6, '2012-01-01', '2012-12-31', 'test', '2012-12-16', '2012-12-16')");
			stmt.executeUpdate("INSERT INTO admin_alluvus VALUES (6, 5, 7, '2012-01-01', '2012-12-31', 'test', '2012-12-16', '2012-12-16')");*/

		} finally {
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}
	}

}
