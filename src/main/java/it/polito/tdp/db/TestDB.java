package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDB {

	public static void main(String[] args) {
						//db che usiamo:mysql  //nome		
		String jdbcURL="jdbc:mysql://localhost/ufo_sightings?user=root&password=root"; //per ora abbiamo semplicemente scritto una stringa 
								//dove sta il db
		try {
			Connection conn=DriverManager.getConnection(jdbcURL);
			
			//QUERY SENZA PARAMETRI
			String sql="SELECT DISTINCT shape " //mi devo ricordare gli spazi alla fine! E' come se fosse scritta tutta su una riga!!
					+ "FROM sighting "
					+ "WHERE shape<>'' "
					+ "ORDER BY shape ASC" ;
			
			PreparedStatement st=conn.prepareStatement(sql);
			
			ResultSet res=st.executeQuery();
			
			List<String> formeUFO=new ArrayList<String>();			
			while(res.next()) {
				String forma=res.getString("shape"); //nome della colonna: c'e' anche nella query
				formeUFO.add(forma);
			}
			st.close();//per liberare la memoria, dipende da quanti statement ho.. posso anche non metterla
			System.out.println(formeUFO);
			
			//QUERY CON PARAMETRI
			String sql2="SELECT COUNT(*) AS cnt "
					+ "FROM sighting "
					+ "WHERE shape= ? "; //? dove ci sono i parametri
			String shapeScelta="circle" ;
			
			PreparedStatement st2=conn.prepareStatement(sql2);
			
			st2.setString(1, shapeScelta);
			ResultSet res2=st.executeQuery();
			
			res2.first();//o next
			int count=res2.getInt("cnt");//metto un nome significativo
			st2.close();
			
			System.out.println("UFO di forma "+shapeScelta+"sono: "+count);
			
			conn.close();//DEVO SEMPRE METTERLA!
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
