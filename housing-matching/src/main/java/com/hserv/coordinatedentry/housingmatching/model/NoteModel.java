package com.hserv.coordinatedentry.housingmatching.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonRootName("note")
public class NoteModel {
	
	private UUID noteId;
	private String note;
	private UUID userId;
	@JsonDeserialize(using=JsonDateDeserializer.class)	
	@JsonSerialize(using=JsonDateTimeSerializer.class)
	private LocalDateTime noteDate;
	public UUID getNoteId() {
		return noteId;
	}
	public void setNoteId(UUID noteId) {
		this.noteId = noteId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public LocalDateTime getNoteDate() {
		return noteDate;
	}
	public void setNoteDate(LocalDateTime noteDate) {
		this.noteDate = noteDate;
	}
}