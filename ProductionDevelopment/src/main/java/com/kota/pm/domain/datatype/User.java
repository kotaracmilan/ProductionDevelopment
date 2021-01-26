package com.kota.pm.domain.datatype;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User implements Serializable {
	
	private static final long serialVersionUID = 1450153577533090664L;
	
	@Id
	@GeneratedValue
	private int id;
	private String username;
	@Length( min = 5)
	private String password;
	@ManyToMany
	private Set<Role> roles;
	
	
}
