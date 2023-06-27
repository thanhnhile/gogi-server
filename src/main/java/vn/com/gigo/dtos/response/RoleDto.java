package vn.com.gigo.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleDto {
	@JsonProperty("id")
	private Integer id;

	@JsonProperty("name")
	private String name;

	public RoleDto() {
	}

	public RoleDto(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
