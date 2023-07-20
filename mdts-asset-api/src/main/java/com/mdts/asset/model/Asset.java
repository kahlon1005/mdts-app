package com.mdts.asset.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ASSET")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
	
	@Id
	@Column(name="ASSET_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@NotBlank(message = "Asset name can not be blank")
	@Column(name="ASSET_NAME", nullable = false)
	private String name;
	
	@NotBlank(message = "Asset type can not be blank")
	@Size(min = 3, message = "Assert type must be 3 character or more")
	@Column(name="ASSET_TYPE", nullable = false)
	private String type;
	
	@Column(name="ASSET_DESC")
	private String description;
	
	@Column(name="CREATED_DATE", updatable = false)
	@CreationTimestamp
	private Timestamp creationTime;
	

}
