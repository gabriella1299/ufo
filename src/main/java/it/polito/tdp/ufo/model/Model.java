package it.polito.tdp.ufo.model;
import java.util.List;

import it.polito.tdp.db.SightingDAO;

public class Model {
	
	private List<String> formeUFO = null ;
	
	public int getCountByForma(String forma) {
		SightingDAO dao = new SightingDAO();
		return dao.countByShape(forma) ;
	}

	public List<String> getFormeUFO() {
		if (this.formeUFO==null) {
			SightingDAO dao = new SightingDAO() ;
			this.formeUFO = dao.readShapes() ;
		}
		return this.formeUFO;
	}

}
