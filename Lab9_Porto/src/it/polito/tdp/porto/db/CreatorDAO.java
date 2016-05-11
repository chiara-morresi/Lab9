package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.porto.model.Creator;

public class CreatorDAO {
	
	public List<Creator> getAutori() {
		List<Creator> autori = new ArrayList<Creator>();
		
		Connection conn = DBConnect.getConnection();
		String sql = "select * from creator order by family_name";
		PreparedStatement st;
		try {
			
			st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while(res.next()) 
			{
				autori.add(new Creator(
						res.getInt("id_creator"),
						res.getString("family_name"),
						res.getString("given_name")
						));
			}
			
			res.close();
			conn.close();
			return autori;
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
		
		
	}

}
