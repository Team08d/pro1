package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import org.apache.commons.dbutils.DbUtils;

public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String db_path = "jdbc:hsqldb:file:${user.home}/i377/Team08d/db;shutdown=true"; 

	public void init() throws ServletException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("<h1>Hello world!!</h1>");
		
		// andmete pärimise test
		List<RiigiAdminYksuseLiikDAO> riigiAdminYksuseLiikDAOs = null;
		
		try {
			riigiAdminYksuseLiikDAOs = getRiigiAdminYksuseLiikDAOs();
			for (RiigiAdminYksuseLiikDAO raylDAO : riigiAdminYksuseLiikDAOs) {
				System.out.println(raylDAO.getNimetus());
			}
		} catch (SQLException e) {
			response.getWriter().print("Viga: getRiigiAdminYksuseLiikDAOs");
			System.out.println(e);
		}
	}

	private List<RiigiAdminYksuseLiikDAO> getRiigiAdminYksuseLiikDAOs() throws SQLException {
		
		List<AdminAlluvusDAO> adminAlluvusDAOs = new ArrayList<AdminAlluvusDAO>();
		List<RiigiAdminYksuseLiikDAO> riigiAdminYksuseLiikDAOs = new ArrayList<RiigiAdminYksuseLiikDAO>();
		
		Connection conn = DriverManager.getConnection(db_path);

		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery("SELECT * FROM riigi_admin_yksuse_liik");
			
			while (rset.next()) {
				RiigiAdminYksuseLiikDAO riigiAdminYksuseLiikDAO = new RiigiAdminYksuseLiikDAO();
				riigiAdminYksuseLiikDAO.setRiigiAdminYksuseLiikId(rset.getInt(1));
				riigiAdminYksuseLiikDAO.setKood(rset.getString(2));
				riigiAdminYksuseLiikDAO.setNimetus(rset.getString(3));
				riigiAdminYksuseLiikDAO.setKommentaar(rset.getString(4));
				riigiAdminYksuseLiikDAO.setAlates(rset.getDate(5));
				riigiAdminYksuseLiikDAO.setKuni(rset.getDate(6));
				riigiAdminYksuseLiikDAO.setAlates(rset.getDate(7));
				riigiAdminYksuseLiikDAO.setKuni(rset.getDate(8));
				
				riigiAdminYksuseLiikDAOs.add(riigiAdminYksuseLiikDAO);
			}
		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}
		
		return riigiAdminYksuseLiikDAOs;
	}

}
