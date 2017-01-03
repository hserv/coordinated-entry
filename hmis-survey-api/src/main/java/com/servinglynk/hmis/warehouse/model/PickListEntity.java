package com.servinglynk.hmis.warehouse.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="PICKLIST_VALUE",schema="survey")
public class PickListEntity extends BaseEntity {

	@Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	
	@Column(name="PICKLIST_VALUE_CODE")
	private String pickListValueCode;
	
	@Column(name="VALUE_TEXT")
	private String valueText;
	
	@ManyToOne
	@JoinColumn(name="PICKLIST_GROUP_ID")
	private PickListGroupEntity pickListGroupEntity;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getPickListValueCode() {
		return pickListValueCode;
	}
	public void setPickListValueCode(String pickListValueCode) {
		this.pickListValueCode = pickListValueCode;
	}
	public String getValueText() {
		return valueText;
	}
	public void setValueText(String valueText) {
		this.valueText = valueText;
	}
	public PickListGroupEntity getPickListGroupEntity() {
		return pickListGroupEntity;
	}
	public void setPickListGroupEntity(PickListGroupEntity pickListGroupEntity) {
		this.pickListGroupEntity = pickListGroupEntity;
	}
}