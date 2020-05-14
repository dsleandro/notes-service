package com.leandro.notesservice.controller;

import java.util.List;

import javax.validation.Valid;

import com.leandro.notesservice.entity.Note;
import com.leandro.notesservice.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    @Qualifier("service")
    NoteService service;

    @PostMapping("/")
    public boolean addNote(@RequestBody @Valid Note note, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return false;

        } else if (note.getTitle().isEmpty() && note.getContent().isEmpty()) {

            return false;

        } else {

            return service.post(note);

        }

    }

    @PutMapping("/{id}")
    public boolean updateNote(@PathVariable("id") long id, @RequestBody @Valid Note note, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return false;

        } else if (note.getTitle().isEmpty() && note.getContent().isEmpty()) {

            return false;

        } else {

            return service.update(id, note);

        }
    }

    @DeleteMapping("/{id}")
    public boolean deleteNote(@PathVariable("id") long id) {

        return service.delete(id);
    }

    @GetMapping("/")
    public List<Note> getAll(@RequestHeader("username") String username) {
        return service.get(username);
    }

    @GetMapping("/{id}")
    public Note getNote(@PathVariable("id") long id) {
        return service.getOne(id).orElse(null);
    }

    @GetMapping("/search")
    public List<Note> getSearch(@RequestHeader("username") String username, @RequestParam("query") String regex) {
        return service.search(username, regex);
    }
}