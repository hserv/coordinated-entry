package com.hserv.coordinatedentry.housinginventory.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hserv.coordinatedentry.housinginventory.domain.Note;
import com.hserv.coordinatedentry.housinginventory.service.NoteService;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.HeaderUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.PaginationUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.NoteDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.NoteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing Note.
 */
@RestController
@RequestMapping("/api")
public class NoteResource {

    private final Logger log = LoggerFactory.getLogger(NoteResource.class);
        
    @Inject
    private NoteService noteService;
    
    @Inject
    private NoteMapper noteMapper;
    
    /**
     * POST  /notes : Create a new note.
     *
     * @param noteDTO the noteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new noteDTO, or with status 400 (Bad Request) if the note has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/notes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<NoteDTO> createNote(@RequestBody NoteDTO noteDTO) throws URISyntaxException {
        log.debug("REST request to save Note : {}", noteDTO);
        if (noteDTO.getNoteId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("note", "idexists", "A new note cannot already have an ID")).body(null);
        }
        NoteDTO result = noteService.save(noteDTO);
        return ResponseEntity.created(new URI("/api/notes/" + result.getNoteId()))
            .headers(HeaderUtil.createEntityCreationAlert("note", result.getNoteId().toString()))
            .body(result);
    }

    /**
     * PUT  /notes : Updates an existing note.
     *
     * @param noteDTO the noteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated noteDTO,
     * or with status 400 (Bad Request) if the noteDTO is not valid,
     * or with status 500 (Internal Server Error) if the noteDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/notes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<NoteDTO> updateNote(@RequestBody NoteDTO noteDTO) throws URISyntaxException {
        log.debug("REST request to update Note : {}", noteDTO);
        if (noteDTO.getNoteId() == null) {
            return createNote(noteDTO);
        }
        NoteDTO result = noteService.save(noteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("note", noteDTO.getNoteId().toString()))
            .body(result);
    }

    /**
     * GET  /notes : get all the notes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of notes in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/notes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<NoteDTO>> getAllNotes(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Notes");
        Page<Note> page = noteService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/notes");
        return new ResponseEntity<>(noteMapper.notesToNoteDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /notes/:id : get the "id" note.
     *
     * @param id the id of the noteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the noteDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/notes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<NoteDTO> getNote(@PathVariable UUID id) {
        log.debug("REST request to get Note : {}", id);
        NoteDTO noteDTO = noteService.findOne(id);
        return Optional.ofNullable(noteDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /notes/:id : delete the "id" note.
     *
     * @param id the id of the noteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/notes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteNote(@PathVariable UUID id) {
        log.debug("REST request to delete Note : {}", id);
        noteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("note", id.toString())).build();
    }

}
