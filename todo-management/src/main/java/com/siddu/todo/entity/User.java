package com.siddu.todo.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data    // getters & setters
@AllArgsConstructor// constructor using all feilds as arguments
@NoArgsConstructor // constructor with zero arguments
@Entity // to mark  as JPA Entity means to represent these class as a table in database
@Table(name = "users")// if we dont use Table annotation JPA will take class name as table name
public class User {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)   // by JPA take property name as column name, added constraint NOTNULL
	private String name;
	@Column(nullable = false,unique = true) //  by JPA take property name as column name, added constraint NOTNULL,UNIQUE
	private String username;
	@Column(nullable = false,unique = true) //  by JPA take property name as column name, added constraint NOTNULL,UNIQUE
	private String email;
	@Column(nullable = false)  //  by JPA take property name as column name, added constraint NOTNULL
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles",
	       joinColumns =@JoinColumn(name="user_id",referencedColumnName = "id"),
	       inverseJoinColumns =@JoinColumn(name="role_id",referencedColumnName = "id")
			)
	private Set<Role> roles;
}
