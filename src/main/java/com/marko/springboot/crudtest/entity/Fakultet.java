package com.marko.springboot.crudtest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="fakultet")
public class Fakultet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="adresa")
	private String adresa;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefon")
	private String telefon;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="fakultet_student", joinColumns = @JoinColumn(name="fakultet_id"),
	inverseJoinColumns = @JoinColumn(name="student_id"))
	private List<Student> studenti;
	
	
	public Fakultet () {
		
	}

	public Fakultet(String naziv, String adresa, String email, String telefon) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.email = email;
		this.telefon = telefon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	

	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}
	
	
	public void addStudent(Student student) {
		if (studenti==null) {
			studenti = new ArrayList<>();
		} 
		studenti.add(student);
	}
	
	public void deleteStudent(Student student) {
		 
		studenti.remove(student);
	}
	

	@Override
	public String toString() {
		return "Fakultet [id=" + id + ", naziv=" + naziv + ", adresa=" + adresa + ", email=" + email + ", telefon="
				+ telefon + "]";
	}

	
	
	
}
