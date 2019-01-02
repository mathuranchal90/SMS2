package com.sms.profileservice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sms.profileservice.model.audit.DateAudit;

@Entity
@Table(name = "user_profile", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id"})
})
public class UserProfile extends DateAudit {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinTable(name = "users",joinColumns = @JoinColumn(name = "user_id", nullable = false))
    private Long user_id;
	
	@Size(max = 300)
	private String imgUrl;
	
	public UserProfile() {
		
	}

	public UserProfile(Long id, @NotBlank Long user_id, @Size(max = 300) String imgUrl) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	

}
