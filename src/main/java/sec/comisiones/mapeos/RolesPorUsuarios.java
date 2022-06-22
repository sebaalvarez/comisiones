package sec.comisiones.mapeos;

import java.io.Serializable;


@SuppressWarnings("serial")
public class RolesPorUsuarios implements Serializable {

		private int id;
		private int idRol;
		private int idUsuario;

		private String nombreRol;
		private String nombreUsuario;
		
		
		public RolesPorUsuarios() {}
		
		public RolesPorUsuarios(int id, int idRol, int idUsuario) {
			this.id = id;
			this.idRol = idRol;
			this.idUsuario = idUsuario;
		}
		
		public RolesPorUsuarios(Usuarios usr) {
			this.idUsuario = usr.getId();
			this.nombreUsuario = usr.getNombre();
		}

		public RolesPorUsuarios(int id, Roles rol, Usuarios usr) {
			this.id = id;
			this.idRol = rol.getId();
			this.idUsuario = usr.getId();
			this.nombreUsuario = usr.getNombre();
			this.nombreRol = rol.getNombre();
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getIdRol() {
			return idRol;
		}

		public void setIdRol(int idRol) {
			this.idRol = idRol;
		}

		public int getIdUsuario() {
			return idUsuario;
		}

		public void setIdUsuario(int idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getNombreRol() {
			return nombreRol;
		}

		public void setNombreRol(String nombreRol) {
			this.nombreRol = nombreRol;
		}

		public String getNombreUsuario() {
			return nombreUsuario;
		}

		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}
		
		
}
