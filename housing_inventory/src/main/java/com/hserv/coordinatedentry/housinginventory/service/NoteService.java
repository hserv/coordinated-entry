package com.hserv.coordinatedentry.housinginventory.service;

import com.hserv.coordinatedentry.housinginventory.domain.Note;
import com.hserv.coordinatedentry.housinginventory.repository.NoteRepository;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.NoteDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.NoteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Note.
 */
@Service
@Transactional
public class NoteService {

    private final Logger log = LoggerFactory.getLogger(NoteService.class);
    
    @Inject
    private NoteRepository noteRepository;
    
    @Inject
    private NoteMapper noteMapper;
    
    /**
     * Save a note.
     * 
     * @param noteDTO the entity to save
     * @return the persisted entity
     */
    public NoteDTO save(NoteDTO noteDTO) {
        log.debug("Request to save Note : {}", noteDTO);
        Note note = noteMapper.noteDTOToNote(noteDTO);
        note.setNoteId(UUID.randomUUID());
        note = noteRepository.save(note);
        NoteDTO result = noteMapper.noteToNoteDTO(note);
        return result;
    }

    /**
     *  Get all the notes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Note> findAll(Pageable pageable) {
        log.debug("Request to get all Notes");
        Page<Note> result = noteRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one note by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public NoteDTO findOne(UUID id) {
        log.debug("Request to get Note : {}", id);
        Note note = noteRepository.findOne(id);
        NoteDTO noteDTO = noteMapper.noteToNoteDTO(note);
        return noteDTO;
    }

    /**
     *  Delete the  note by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(UUID id) {
        log.debug("Request to delete Note : {}", id);
        noteRepository.delete(id);
    }
}
