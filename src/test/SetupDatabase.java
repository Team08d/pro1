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
	
	private String db_path = "c:/java_i377_prax/mem_database/prax3"; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Loading data...");

		try {
			createTables();
			response.getWriter().println("Success!");
		} catch (Exception e) {
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
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:" + db_path + ";shutdown=true");

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
			// LISAME TEST KIRJE
			stmt.executeUpdate("INSERT INTO riigi_admin_yksuse_liik VALUES (1, 'TST', 'SEE ON TEST', 'test komm', '2012-01-01', '2012-12-31', '2012-12-16', '2012-12-16')");

			// VOIMALIK_ALLUVUS - ainult kasutatavad väljad
			stmt.executeUpdate("CREATE TABLE voimalik_alluvus (voimalik_alluvus_id IDENTITY," +
															  "riigi_admin_yksuse_liik_id INTEGER NOT NULL," +
															  "voimalik_alluv_liik_id INTEGER NOT NULL," +
															  "kommentaar LONGVARCHAR NOT NULL," +
															  "avatud DATE NOT NULL," +
															  "muudetud DATE NOT NULL," +
															  "FOREIGN KEY (riigi_admin_yksuse_liik_id, voimalik_alluv_liik_id)" +
															  "REFERENCES riigi_admin_yksuse_liik(riigi_admin_yksuse_liik_id))");
			
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

		} finally {
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}
	}

}
