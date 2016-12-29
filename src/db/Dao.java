package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Parola;

public class Dao {

	public boolean isPresente(String nome){
		Connection conn = DBConnect.getConnection();
		String query ="select * from parola where nome=? ";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, nome);
			ResultSet res = st.executeQuery();
			if(res.next()){
				return true;
			}else 
				{return false;}	
		}catch(SQLException e ){
			e.printStackTrace();
			return false;
		}
	}
	
	public int conta(int lun){
		Connection conn = DBConnect.getConnection();
		String query =" select count(*) as numero from parola where LENGTH(nome)=?";
		try{
			int numero=-1;
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, lun);
			ResultSet res = st.executeQuery();
			while(res.next()){
				numero = res.getInt("numero");
			}
			conn.close();
			return numero;
		}catch(SQLException e ){
			e.printStackTrace();
			return -1;
		}

	}
	
	public List<Parola> getAllParole(int lung){
		Connection conn = DBConnect.getConnection();
		String query = "select * from parola where LENGTH(nome)=?";
		try{
			List<Parola> pa = new LinkedList<Parola>();
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, lung);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Parola p = new Parola(res.getInt("id"), res.getString("nome"));
				pa.add(p);
			}
			conn.close();
			return pa;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
}
