package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import com.enumerated.tipoPerfil;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
@Table(name="USUARIOS", schema="PROYECTO")
@NamedQueries({
	@NamedQuery(name="Usuario.getAll", query="SELECT u FROM Usuario u"),
	@NamedQuery(name="Usuario.get", query="SELECT u FROM Usuario u WHERE UPPER(u.nomAcceso)=UPPER(:nomAcceso)"),
	@NamedQuery(name="Usuario.getNomAccesoLike", query="SELECT u FROM Usuario u WHERE UPPER(u.nomAcceso) LIKE :nomAcceso"),
})
public class Usuario implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@SequenceGenerator(name="USUARIOS_IDUSUARIO_GENERATOR", sequenceName="SEQ_USUARIO")
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIOS_IDUSUARIO_GENERATOR")
		@Column(name = "USU_CODIGO")
		private Long id;
		
		@Column(length=50,name = "USU_NOMBRE",nullable=false)
		private String nombre;
		
		@Column(length=50,name = "USU_APELLIDO",nullable=false)
		private String apellido;
		
		@Column(length=50,name = "USU_NOMACCESO",nullable=false)
		private String nomAcceso;
		
		@Column(length=16,name = "USU_CONTRASENA",nullable=false)
		private String contrasena;
		
		@Column(length=50,name = "USU_CORREO", nullable=false)
		private String correo;
		
		@Enumerated(EnumType.STRING)
		@Column(name="USU_PERFIL")
		private tipoPerfil tipoPerfil;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getNomAcceso() {
			return nomAcceso;
		}

		public void setNomAcceso(String nomAcceso) {
			this.nomAcceso = nomAcceso;
		}

		public String getContrasena() {
			return contrasena;
		}

		public void setContrasena(String contrasena) {
			this.contrasena = contrasena;
		}

		public String getCorreo() {
			return correo;
		}

		public void setCorreo(String correo) {
			this.correo = correo;
		}

		public tipoPerfil getTipoPerfil() {
			return tipoPerfil;
		}

		public void setTipoPerfil(tipoPerfil tipoPerfil) {
			this.tipoPerfil = tipoPerfil;
		}

		public Usuario() {
		}
		

}
