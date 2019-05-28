package com.caoliyuan.travelGuide.service;

import com.caoliyuan.travelGuide.domain.CommentModel;
import com.caoliyuan.travelGuide.domain.NoteItemModel;
import com.caoliyuan.travelGuide.domain.NoteModel;

import java.util.List;

public interface NoteService {
    public boolean addNote(NoteModel noteModel);
    public boolean addNoteItem(NoteItemModel noteItemModel);
    public List<NoteModel> getNotes(int num,String orderby);
    public NoteModel getNoteByid(long id);
    public List<NoteModel> searchNotes(String title);
    public List<NoteItemModel> getNoteDetialById(long noteId);
    public boolean viewNumPlusByid(long id);
    public List<CommentModel> getNoteCommentByid(long noteid);
    public boolean addComment(CommentModel commentModel);
    public int getNoteNum();
    public boolean deleteNote(long noteid);
}
