package com.servinglynk.hmis.housinginventory.repository;


import org.springframework.data.jpa.repository.*;

import com.servinglynk.hmis.housinginventory.domain.Note;

import java.util.List;

/**
 * Spring Data JPA repository for the Note entity.
 */
public interface NoteRepository extends JpaRepository<Note,Long> {

}
