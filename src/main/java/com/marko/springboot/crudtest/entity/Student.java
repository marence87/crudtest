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
@Table(name="student")
public class Student {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;


	@Column(name="ime")
	private String ime;
	
	@Column(name="prezime")
	private String prezime;
	
	@Column(name="jmbg")
	private String jmbg;
	
	@Column(name="datum_rodjenja")
	private String datum_rodjenja;
	
	@Column(name="email")
	private String email;
	
	@Column(name="adresa")
	private String adresa;
	
	@Column(name="telefon")
	private String telefon;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="fakultet_student", 
			joinColumns=@JoinColumn(name="student_id"),
			inverseJoinColumns=@JoinColumn(name="fakultet_id"))
	private List<Fakultet>fakulteti;
	
	public Student () {
		
	}


	public Student(String ime, String prezime, String jmbg, String datum_rodjenja, String email, String adresa,
			String telefon) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.datum_rodjenja = datum_rodjenja;
		this.email = email;
		this.adresa = adresa;
		this.telefon = telefon;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getJmbg() {
		return jmbg;
	}


	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}


	public String getDatum_rodjenja() {
		return datum_rodjenja;
	}


	public void setDatum_rodjenja(String datum_rodjenja) {
		this.datum_rodjenja = datum_rodjenja;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public String getTelefon() {
		return telefon;
	}


	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	
	public List<Fakultet> getFakulteti() {
		return fakulteti;
	}

	public void setFakulteti(List<Fakultet> fakulteti) {
		this.fakulteti = fakulteti;
	}

	public void addFakultet(Fakultet fakultet) {
		if (fakulteti == null) {
			fakulteti = new ArrayList<>();
		}
		fakulteti.add(fakultet);
	}

	public void deleteFakultet(Fakultet fakultet) {
		fakulteti.remove(fakultet);
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", datum_rodjenja="
				+ datum_rodjenja + ", email=" + email + ", adresa=" + adresa + ", telefon=" + telefon + "]";
	}
	

}
