package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.dbutils.DbUtils;

/**
 * Servlet implementation class AlluvusRaport
 */
public class AlluvusRaport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String db_path = "jdbc:hsqldb:file:${user.home}/i377/Team08d/db;shutdown=true";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<RiigiAdminYksuseLiikDAO> liigid = null;
		try {
			liigid = getLiigid();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		request.setAttribute("liigid", liigid);
		request.getRequestDispatcher("alluvus_raport.jsp").forward(request, response);

	}

	public void init() throws ServletException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private List<RiigiAdminYksuseLiikDAO> getLiigid() throws SQLException {
		
		List<RiigiAdminYksuseLiikDAO> liigid = new ArrayList<RiigiAdminYksuseLiikDAO>();
		
		Connection conn = DriverManager.getConnection(db_path);

		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery("select riigi_admin_yksuse_liik_id, nimetus from riigi_admin_yksuse_liik");

			while (rset.next()) {
				RiigiAdminYksuseLiikDAO liik = new RiigiAdminYksuseLiikDAO();
				liik.setRiigiAdminYksuseLiikId(rset.getInt(1));
				liik.setNimetus(rset.getString(2));

				liigid.add(liik);
			}

			return liigid;
			
		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
