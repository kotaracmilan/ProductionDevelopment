package com.kota.pm.domain.transakcije;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.product.Proizvod;

import lombok.Data;

@Entity
@Data
public class Kartica implements Serializable{

	private static final long serialVersionUID = -3850995453795044719L;
	
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Proizvod proizvod;
	@OneToMany (cascade = CascadeType.ALL)
	private List<Transakcija> transakcije;
	@OneToOne
	private Pogon pogon;
	
	
}
