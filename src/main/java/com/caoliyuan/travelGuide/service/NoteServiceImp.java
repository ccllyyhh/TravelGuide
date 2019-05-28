package com.caoliyuan.travelGuide.service;

import com.caoliyuan.travelGuide.domain.CommentModel;
import com.caoliyuan.travelGuide.domain.NoteItemModel;
import com.caoliyuan.travelGuide.domain.NoteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class NoteServiceImp implements NoteService{
    @Autowired
    private JdbcTemplate jdbcTemplate;



    /*
    * NOTE_ID = nOTE_ID;
		USER_ID = uSER_ID;
		TITLE = tITLE;
		COVER_URL = cOVER_URL;
		VIEW_NUM = vIEW_NUM;
		LIKE_NUM = lIKE_NUM;
		START_DAY = sTART_DAY;
		LOCATION = lOCATION;
    * */


    @Override
    public List<NoteModel> getNotes(int num,String orderby) {
        String sql = "select * from note order by "+orderby+" desc limit ?";
        return jdbcTemplate.query(sql,NoteModel.noteMapper, num);
    }

    @Override
    public List<NoteModel> searchNotes(String title) {
        String sql = "select * from note where title like '%"+title+"%'";
        return jdbcTemplate.query(sql, NoteModel.noteMapper);
    }

    @Override
    public List<NoteItemModel> getNoteDetialById(long noteId) {
        String sql = "select * from noteitem where note_id = ? order by note_item_id";
        return jdbcTemplate.query(sql,NoteItemModel.noteItemMapper, noteId);
    }

    @Override
    public NoteModel getNoteByid(long id) {
        String sql = "select * from note where note_id = ?";
        List<NoteModel> result = jdbcTemplate.query(sql, NoteModel.noteMapper, id);
        return result.size()>0?result.get(0):null;
    }

    @Override
    public boolean viewNumPlusByid(long id) {
        String sql = "update note set view_num=view_num+1 where note_id = ?";
        int rows = jdbcTemplate.update(sql,id);
        return rows>0;
    }

    @Override
    public List<CommentModel> getNoteCommentByid(long noteid) {
        String sql = "select * from comment where note_id = ?";
        return jdbcTemplate.query(sql,CommentModel.commentMapper, noteid);
    }

    @Override
    public boolean addComment(CommentModel commentModel) {
        String sql = "insert into comment value(NULL,?,?,?,NUll)";
        int rows = jdbcTemplate.update(sql, commentModel.getNoteid(), commentModel.getUserid(),commentModel.getContent());
        return rows>0;
    }

    @Override
    public boolean addNote(NoteModel noteModel) {
        String sql = "insert into note value(?,?,?,?,?,?,?,?,?)";
        int rows = jdbcTemplate.update(sql,
                noteModel.getNOTE_ID(),
                noteModel.getUSER_ID(),
                noteModel.getTITLE(),
                noteModel.getCOVER_URL(),
                noteModel.getVIEW_NUM(),
                noteModel.getLIKE_NUM(),
                noteModel.getSTART_DAY(),
                noteModel.getLOCATION(),
                noteModel.getCREATETIME());
        return rows>0;
    }

    @Override
    public boolean addNoteItem(NoteItemModel noteItemModel) {
        String sql = "insert into noteitem value(NULL,?,?,?,?,?,?)";
        int rows = jdbcTemplate.update(sql,
                noteItemModel.getNOTE_ID(),
                noteItemModel.getDAY_NUM(),
                noteItemModel.getIMG_URL(),
                noteItemModel.getCONTENT(),
                noteItemModel.getTIME(),
                noteItemModel.getLOCATION()
                );
        return rows>0;
    }

    @Override
    public int getNoteNum() {
        String sql = "select count(*) from note";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public boolean deleteNote(long noteid) {
        String sql = "delete from note where NOTE_ID = ?";
        int r = jdbcTemplate.update(sql,noteid);
        return r>0;
    }
}
