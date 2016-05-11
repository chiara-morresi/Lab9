package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.porto.db.DBConnect;
import it.polito.tdp.porto.model.Article;
import it.polito.tdp.porto.model.Creator;

public class PortoDAO {
	
	
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
	
	
	
	public List<Creator> getCoautori(Creator c) {
		List<Creator> coautori = new ArrayList<Creator>();
		
		Connection conn = DBConnect.getConnection();
		String sql = "select distinct creator.`id_creator`, creator.`family_name`, creator.`given_name` from creator, authorship "
				+ "where creator.`id_creator`= authorship.`id_creator` and "
				+ "authorship.`eprintid` in (select eprintid from authorship where id_creator = ?)"
				+ "order by creator.`family_name`";
		PreparedStatement st;
		try {
			
			st = conn.prepareStatement(sql);
			st.setInt(1, c.getId_creator());
			
	
			ResultSet res = st.executeQuery();
			
			while(res.next()) 
			{
				coautori.add(new Creator(
						res.getInt("id_creator"),
						res.getString("family_name"),
						res.getString("given_name")
						));
			}
			
			res.close();
			conn.close();
			return coautori;
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
		
		
	}
	
	
	public List<Article> getArticle(Creator c1, Creator c2) {
		List<Article> articoli = new ArrayList<Article>();
		
		Connection conn = DBConnect.getConnection();
		String sql = "select distinct article.`eprintid`, article.`title`, article.`year` from article, authorship where article.`eprintid`=authorship.`eprintid` and authorship.`eprintid` in "
				+ "(select authorship.`eprintid` from authorship, creator where authorship.`id_creator`=creator.`id_creator` and creator.`id_creator`=?) and authorship.`eprintid` in "
				+ "(select authorship.`eprintid` from authorship, creator where authorship.`id_creator`=creator.`id_creator` and creator.`id_creator`=?)";
		PreparedStatement st;
		try {
			
			st = conn.prepareStatement(sql);
			st.setInt(1, c1.getId_creator());
			st.setInt(2, c2.getId_creator());
			
	
			ResultSet res = st.executeQuery();
			
			while(res.next()) 
			{
				articoli.add(new Article(
						res.getLong("eprintid"),
						res.getInt("year"),
						res.getString("title")
						));
			}
			
			res.close();
			conn.close();
			return articoli;
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
		
		
	}
	
	
	

}
