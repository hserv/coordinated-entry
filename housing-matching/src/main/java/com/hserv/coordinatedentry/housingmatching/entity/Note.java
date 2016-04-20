package com.hserv.coordinatedentry.housingmatching.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "note", schema = "ces")
public class Note implements java.io.Serializable {

	private String noteId;
	private MatchReservations matchReservations;
	private String noteString;

	public Note() {
	}

	public Note(String noteId, MatchReservations matchReservations) {
		this.noteId = noteId;
		this.matchReservations = matchReservations;
	}

	public Note(String noteId, MatchReservations matchReservations, String noteString) {
		this.noteId = noteId;
		this.matchReservations = matchReservations;
		this.noteString = noteString;
	}

	//@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "note_id", unique = true, nullable = false)
	public String getNoteId() {
		return this.noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation_id", nullable = false)
	public MatchReservations getMatchReservations() {
		return this.matchReservations;
	}

	public void setMatchReservations(MatchReservations matchReservations) {
		this.matchReservations = matchReservations;
	}

	@Column(name = "note_string")
	public String getNoteString() {
		return this.noteString;
	}

	public void setNoteString(String noteString) {
		this.noteString = noteString;
	}

}
