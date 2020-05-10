package com.leandro.notesservice.repository;

import java.util.List;

import com.leandro.notesservice.entity.Note;
import com.leandro.notesservice.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repository")
public interface NoteRepository extends JpaRepository<Note,Long>{

    Note findById(long id);

    List<Note> findByUser(User user);

    @Query(value="SELECT * FROM notes WHERE user = ?1 AND (title REGEXP ?2 OR content REGEXP ?2)", nativeQuery=true)
    List<Note> findByUserAndTitleOrContent(String username, String regex);
}