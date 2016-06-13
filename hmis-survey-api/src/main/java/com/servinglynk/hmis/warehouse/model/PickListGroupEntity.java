package com.servinglynk.hmis.warehouse.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="PICKLIST_GROUP",schema="survey")
@SQLDelete(sql="UPDATE survey.PICKLIST_GROUP SET IS_ACTIVE = 'FALSE' WHERE ID =?")
@Where(clause=" IS_ACTIVE = 'TRUE' ")
public class PickListGroupEntity extends BaseEntity {

	@Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	
	@Column(name="PICKLIST_GROUP_NAME")
	private String pickListGroupName;
	
	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="pickListGroupEntity",fetch=FetchType.LAZY)
	@Where(clause=" IS_ACTIVE = 'TRUE' ")
	List<PickListValueEntity> pickListValueEntities = new ArrayList<PickListValueEntity>();
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getPickListGroupName() {
		return pickListGroupName;
	}
	public void setPickListGroupName(String pickListGroupName) {
		this.pickListGroupName = pickListGroupName;
	}
	public List<PickListValueEntity> getPickListValueEntities() {
		return pickListValueEntities;
	}
	public void setPickListValueEntities(List<PickListValueEntity> pickListValueEntities) {
		this.pickListValueEntities = pickListValueEntities;
	}
}