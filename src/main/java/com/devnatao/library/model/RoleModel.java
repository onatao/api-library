package com.devnatao.library.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import com.devnatao.library.enums.RoleName;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ROLE")
public class RoleModel implements GrantedAuthority, Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID roleId;
	@Enumerated(EnumType.STRING) // identify as STRING value on db
	private RoleName roleName;
	
	public UUID getRoleId() {
		return roleId;
	}
	
	public void setRoleId(UUID roleId) {
		this.roleId = roleId;
	}
	
	public RoleName getRoleName() {
		return roleName;
	}
	
	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}

	@Override
	public String getAuthority() {
		return this.roleName.toString();
	}
}
