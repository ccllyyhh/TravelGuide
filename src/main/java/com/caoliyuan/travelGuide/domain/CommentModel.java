package com.caoliyuan.travelGuide.domain;

public class CommentModel {

    private int commentid;

    private int noteid;

    private int userid;

    private String content;

    private long time;

    public CommentModel(int noteid, int userid, String content, long time) {
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

    public int getNoteid() {
        return noteid;
    }

    public void setNoteid(int noteid) {
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
