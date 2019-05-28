package com.caoliyuan.travelGuide.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentModel {

    private int commentid;

    private long noteid;

    private int userid;

    private String content;

    private long time;

    public static RowMapper<CommentModel> commentMapper = new RowMapper<CommentModel>() {
        @Override
        public CommentModel mapRow(ResultSet resultSet, int i) throws SQLException {
            return new CommentModel(
                    resultSet.getLong("note_id"),
                    resultSet.getInt("user_id"),
                    resultSet.getString("content"),
                    resultSet.getTimestamp("create_date").getTime()
            );
        }
    };

    public CommentModel(long noteid, int userid, String content, long time) {
        this.noteid = noteid;
        this.userid = userid;
        this.content = content;
        this.time = time;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public long getNoteid() {
        return noteid;
    }

    public void setNoteid(long noteid) {
        this.noteid = noteid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
