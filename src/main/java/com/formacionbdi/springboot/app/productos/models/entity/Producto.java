package com.formacionbdi.springboot.app.productos.models.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;
@Data
@Entity
@Table(name="productos")
public class Producto implements Serializable{
	private static final long serialVersionUID = -3705505440435868427L;
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto", unique = true)
	private Long id;
	private String nombre;
	private Double precio;
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@Transient
	private Integer port;
	
}
