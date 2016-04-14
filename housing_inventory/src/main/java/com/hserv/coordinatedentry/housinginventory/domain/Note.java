package com.hserv.coordinatedentry.housinginventory.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Note.
 */
@Entity
@Table(name = "note")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Note extends HousingInventoryBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "note_id")
    private UUID noteId;

    @Column(name = "note_string")
    private String noteString;

	public UUID getNoteId() {
		return noteId;
	}

	public void setNoteId(UUID noteId) {
		this.noteId = noteId;
	}

	public String getNoteString() {
		return noteString;
	}

	public void setNoteString(String noteString) {
		this.noteString = noteString;
	}

   
}
