package com.leandro.notesservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.leandro.notesservice.entity.Note;
import com.leandro.notesservice.entity.User;
import com.leandro.notesservice.repository.NoteRepository;
import com.leandro.notesservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("service")
public class NoteService {

    @Autowired
    @Qualifier("repository")
    private NoteRepository noteRepository;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;
    

    public boolean post(Note note){
        try {
            noteRepository.save(note);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(long id, Note noteUpdated){
        try {
            Note note = noteRepository.findById(id);

            note.setTitle(noteUpdated.getTitle());
            note.setContent(noteUpdated.getContent());
            note.setDate(new Date());

            noteRepository.save(note);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(long id){

        try {
            Note note = noteRepository.findById(id);
            noteRepository.delete(note);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Note> get(String username){

        User user = userRepository.findByUsername(username);

        if (user != null) {
            return user.getNotes();
        } else {
            return null;
        }
       
    }

    //Return Note or null
    public Optional<Note> getOne(Long id){ 
       return noteRepository.findById(id);
    }

    public List<Note> search(String user, String regex){

        //send username and trimed request
        List<Note> notes = noteRepository.findByUserAndTitleOrContent(user,
        regex.trim());

        return notes;
    }

}