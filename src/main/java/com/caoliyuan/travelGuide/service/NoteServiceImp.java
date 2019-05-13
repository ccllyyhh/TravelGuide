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

    @Override
    public boolean addNote() {
        return false;
    }


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
        return jdbcTemplate.query(sql, new RowMapper<NoteModel>() {
            @Override
            public NoteModel mapRow(ResultSet resultSet, int i) throws SQLException {
                return new NoteModel(
                        resultSet.getInt("note_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("title"),
                        resultSet.getString("cover_url"),
                        resultSet.getInt("view_num"),
                        resultSet.getInt("like_num"),
                        resultSet.getString("start_day"),
                        resultSet.getString("location")
                );
            }
        }, num);
    }

    @Override
    public List<NoteModel> searchNotes(String title) {
        String sql = "select * from note where title like '%"+title+"%'";
        return jdbcTemplate.query(sql, new RowMapper<NoteModel>() {
            @Override
            public NoteModel mapRow(ResultSet resultSet, int i) throws SQLException {
                return new NoteModel(
                        resultSet.getInt("note_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("title"),
                        resultSet.getString("cover_url"),
                        resultSet.getInt("view_num"),
                        resultSet.getInt("like_num"),
                        resultSet.getString("start_day"),
                        resultSet.getString("location")
                );
            }
        });
    }

    @Override
    public List<NoteItemModel> getNoteDetialById(int noteId) {
        String sql = "select * from noteitem where note_id = ? order by note_item_id";
        return jdbcTemplate.query(sql, new RowMapper<NoteItemModel>() {
            @Override
            public NoteItemModel mapRow(ResultSet resultSet, int i) throws SQLException {
                return new NoteItemModel(
                        resultSet.getInt("note_item_id"),
                        resultSet.getInt("note_id"),
                        resultSet.getInt("day_num"),
                        resultSet.getString("img_url"),
                        resultSet.getString("content"),
                        resultSet.getString("time"),
                        resultSet.getString("location")
                );
            }
        }, noteId);
    }

    @Override
    public NoteModel getNoteByid(int id) {
        String sql = "select * from note where note_id = ?";
        List<NoteModel> result = jdbcTemplate.query(sql, new RowMapper<NoteModel>() {
            @Override
            public NoteModel mapRow(ResultSet resultSet, int i) throws SQLException {
                return new NoteModel(
                        resultSet.getInt("note_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("title"),
                        resultSet.getString("cover_url"),
                        resultSet.getInt("view_num"),
                        resultSet.getInt("like_num"),
                        resultSet.getString("start_day"),
                        resultSet.getString("location")
                );
            }
        },id);
        return result.size()>0?result.get(0):null;
    }

    @Override
    public boolean viewNumPlusByid(int id) {
        String sql = "update note set view_num=view_num+1 where note_id = ?";
        int rows = jdbcTemplate.update(sql,id);
        return rows>0;
    }

    @Override
    public List<CommentModel> getNoteCommentByid(int noteid) {
        String sql = "select * from comment where note_id = ?";
        return jdbcTemplate.query(sql, new RowMapper<CommentModel>() {
            @Override
            public CommentModel mapRow(ResultSet resultSet, int i) throws SQLException {
                return new CommentModel(
                        resultSet.getInt("note_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("time").getTime()
                );
            }
        }, noteid);
    }

    @Override
    public boolean addComment(CommentModel commentModel) {
        String sql = "insert into comment value(NULL,?,?,?,NUll)";
        int rows = jdbcTemplate.update(sql, commentModel.getNoteid(), commentModel.getUserid(),commentModel.getContent());
        return rows>0;
    }
}
