package br.com.csvreader.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.csvreader.model.School;
import br.com.csvreader.util.Conexao;

public class SchoolDB {
	
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SchoolDB() {
		connection = Conexao.getConnection();
	}
	
	public boolean insert(School s) throws SQLException {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("insert into school (ID, SchoolCode, SchoolName, Address, City, StateCode, ZipCode, Province, Country, PostalCode) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			stmt.setString(1, String.valueOf(s.getID()));
			stmt.setString(2, s.getSchoolCode());
			stmt.setString(3, s.getSchoolName());
			stmt.setString(4, s.getAddress());
			stmt.setString(5, s.getCity());
			stmt.setString(6, s.getStateCode());
			stmt.setString(7, s.getZipCode());
			stmt.setString(8, s.getProvince());
			stmt.setString(9, s.getCountry());
			stmt.setString(10, s.getPostalCode());
			
			stmt.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e.toString());
		} finally {
			connection.close();
		}
		
		return false;
	}
	
	public List<School> getAll() throws SQLException {
		List<School> schools = new ArrayList<>();
		
		try {
			ps = this.connection.prepareStatement("select * from school");
			rs = ps.executeQuery();

			while (rs.next()) {
				School school = new School();
				
				school.setID(rs.getInt("ID"));
				school.setSchoolCode(rs.getString("SchoolCode"));
				school.setSchoolName(rs.getString("SchoolName"));
				school.setAddress(rs.getString("Address"));
				school.setCity(rs.getString("City"));
				school.setStateCode(rs.getString("StateCode"));
				school.setZipCode(rs.getString("ZipCode"));
				school.setProvince(rs.getString("Province"));
				school.setCountry(rs.getString("Country"));
				school.setPostalCode(rs.getString("PostalCode"));
				
				schools.add(school);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return schools;
	}
}