package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SightingDAO {

	public List<String> readShapes(){
		
		try {
			Connection conn = DBconnect.getConnection();
			
			//QUERY SENZA PARAMETRI
			String sql="SELECT DISTINCT shape " //mi devo ricordare gli spazi alla fine! E' come se fosse scritta tutta su una riga!!
					 + "FROM sighting "
					 + "WHERE shape<>'' "
					 + "ORDER BY shape ASC " ;
		
			PreparedStatement st=conn.prepareStatement(sql);
		
			ResultSet res=st.executeQuery();
		
			List<String> formeUFO=new ArrayList<String>();			
			while(res.next()) {
				String forma=res.getString("shape"); //nome della colonna: c'e' anche nella query
				formeUFO.add(forma);
			}
			st.close();//per liberare la memoria, dipende da quanti statement ho.. posso anche non metterla
			conn.close();
			return formeUFO;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Datebase error in readShapes", e);
		}
		
	}
	
	public int countByShape(String shape) {
		
		try {
			Connection conn = DBconnect.getConnection();
			
			//QUERY CON PARAMETRI
			String sql2="SELECT COUNT(*) AS cnt "
					  + "FROM sighting "
					  + "WHERE shape= ? "; //? dove ci sono i parametri
			
			
			PreparedStatement st2=conn.prepareStatement(sql2);
			
			st2.setString(1, shape);
			ResultSet res2=st2.executeQuery();
			
			res2.first();//o next
			int count=res2.getInt("cnt");//metto un nome significativo al count della query
			st2.close();
			conn.close();
			
			return count;
			
		}catch(SQLException e){
			throw new RuntimeException("Datebase error in countByShape", e);
		}
	}
}
