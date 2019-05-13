package com.caoliyuan.travelGuide.service;

import com.caoliyuan.travelGuide.domain.CommentModel;
import com.caoliyuan.travelGuide.domain.NoteItemModel;
import com.caoliyuan.travelGuide.domain.NoteModel;

import java.util.List;

public interface NoteService {
    public boolean addNote();
    public List<NoteModel> getNotes(int num,String orderby);
    public NoteModel getNoteByid(int id);
    public List<NoteModel> searchNotes(String title);
    public List<NoteItemModel> getNoteDetialById(int noteId);
    public boolean viewNumPlusByid(int id);
    public List<CommentModel> getNoteCommentByid(int noteid);
    public boolean addComment(CommentModel commentModel);
}
