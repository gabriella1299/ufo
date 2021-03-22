package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {//conosce la stringa di connessione
						//fornisce metodo per fornire la connessione stessa
						//tutte le classi si appoggiano qui per conoscere la connessione

	public static Connection getConnection() throws SQLException{
							//db che usiamo:mysql  //nome		
		String jdbcURL="jdbc:mysql://localhost/ufo_sightings?user=root&password=root"; //per ora abbiamo semplicemente scritto una stringa 
				//dove sta il db
		return DriverManager.getConnection(jdbcURL);
	}
}
