package com.hserv.coordinatedentry.housinginventory.web.rest.mapper;

import com.hserv.coordinatedentry.housinginventory.domain.*;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.NoteDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Note and its DTO NoteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NoteMapper {

    NoteDTO noteToNoteDTO(Note note);

    List<NoteDTO> notesToNoteDTOs(List<Note> notes);

    Note noteDTOToNote(NoteDTO noteDTO);

    List<Note> noteDTOsToNotes(List<NoteDTO> noteDTOs);
}
