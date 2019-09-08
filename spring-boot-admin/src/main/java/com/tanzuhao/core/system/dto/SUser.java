package com.tanzuhao.core.system.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tanzuhao.core.base.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 用户名密码信息
 * 
 * @author Veiking
 */
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "S_USER")
public class SUser extends BaseDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7674790617805875728L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String password;
    
	public SUser() {
		super();
	}
	

	public SUser(Long id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}