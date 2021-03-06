package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbExcepion;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO department(Name) VALUES(?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			
			int rowsAff = st.executeUpdate();
			
			if (rowsAff > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			} else {
				throw new DbExcepion("Unexpected error! No rows affected!");
			}
			
		} catch (SQLException e) {
			throw new DbExcepion(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM department " + 
					"where Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
				return dep;
			}
			return null;
		} catch (SQLException e) {
			throw new DbExcepion(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Department> listDep = new ArrayList<Department>();
		
		try {
			st = conn.prepareStatement("SELECT * FROM department");
			rs = st.executeQuery();
			
			while (rs.next()) {
				listDep.add(new Department(rs.getInt("Id"), rs.getString("name")));
			}
			return listDep;

		} catch (SQLException e) {
			throw new DbExcepion(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}
