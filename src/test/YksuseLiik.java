package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;

public class YksuseLiik extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String db_path = "c:/java_i377_prax/mem_database/prax3"; 

	public void init() throws ServletException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String yksuseLiikId = request.getParameter("id");
		RiigiAdminYksuseLiikDAO riigiAdminYksuseLiikDAO = null;
		
		if (yksuseLiikId != null) {
			try {
				riigiAdminYksuseLiikDAO = getYksuseLiikById(Long.parseLong(yksuseLiikId));
				System.out.println(riigiAdminYksuseLiikDAO.getKommentaar());
			} catch (SQLException e) {
				System.out.println("Viga: getYksuseLiikById");
				System.out.println(e);
			}
		}
		
		request.setAttribute("yksuseLiik", riigiAdminYksuseLiikDAO);
		addYksuseLiik(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	private RiigiAdminYksuseLiikDAO getYksuseLiikById(Long yksuseLiikId) throws SQLException {
		
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:" + db_path + ";shutdown=true");

		PreparedStatement ps = null;
		ResultSet rset = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM riigi_admin_yksuse_liik WHERE riigi_admin_yksuse_liik_id = ?");
			ps.setLong(1, yksuseLiikId);
			
			rset = ps.executeQuery();

			RiigiAdminYksuseLiikDAO riigiAdminYksuseLiikDAO = new RiigiAdminYksuseLiikDAO();
			
			while (rset.next()) {
				riigiAdminYksuseLiikDAO.setRiigiAdminYksuseLiikId(rset.getInt(1));
				riigiAdminYksuseLiikDAO.setKood(rset.getString(2));
				riigiAdminYksuseLiikDAO.setNimetus(rset.getString(3));
				riigiAdminYksuseLiikDAO.setKommentaar(rset.getString(4));
				riigiAdminYksuseLiikDAO.setAlates(rset.getDate(5));
				riigiAdminYksuseLiikDAO.setKuni(rset.getDate(6));
				riigiAdminYksuseLiikDAO.setAlates(rset.getDate(7));
				riigiAdminYksuseLiikDAO.setKuni(rset.getDate(8));
			}
			
			return riigiAdminYksuseLiikDAO;
		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
	}
	
	private void addYksuseLiik(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("admin_yksuse_liigi_redaktor.jsp").forward(request, response);
	}
}
