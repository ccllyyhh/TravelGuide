package com.caoliyuan.travelGuide.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class NoteModel {
    /**
     * 日记id
     */
    private long NOTE_ID;

    /**
     * 作者id
     */
    private  Integer USER_ID;

    /**
     * 日记标题
     */
    private  String TITLE;

    /**
     * 封面图片
     */
    private String COVER_URL;

    /**
     * 浏览数
     */
    private Integer VIEW_NUM;

    /**
     * 点赞数
     */
    private Integer LIKE_NUM;

    /**
     * 开始日期
     */
    private Date START_DAY;

    /**
     * 地点
     */
    private String LOCATION;

    /*
    * 添加日期
    * */
    private Date CREATETIME;

		public static RowMapper<NoteModel> noteMapper = new RowMapper<NoteModel>() {
				@Override
				public NoteModel mapRow(ResultSet resultSet, int i) throws SQLException {
						return new NoteModel(
								resultSet.getLong("note_id"),
								resultSet.getInt("user_id"),
								resultSet.getString("title"),
								resultSet.getString("cover_url"),
								resultSet.getInt("view_num"),
								resultSet.getInt("like_num"),
								resultSet.getDate("start_day"),
								resultSet.getString("location"),
								resultSet.getDate("create_day")
						);
				}
		};

    public NoteModel() {
    	
    }

		public NoteModel(long NOTE_ID, Integer USER_ID, String TITLE, String COVER_URL, Integer VIEW_NUM, Integer LIKE_NUM, Date START_DAY, String LOCATION, Date CREATETIME) {
				this.NOTE_ID = NOTE_ID;
				this.USER_ID = USER_ID;
				this.TITLE = TITLE;
				this.COVER_URL = COVER_URL;
				this.VIEW_NUM = VIEW_NUM;
				this.LIKE_NUM = LIKE_NUM;
				this.START_DAY = START_DAY;
				this.LOCATION = LOCATION;
				this.CREATETIME = CREATETIME;
		}

		public Date getCREATETIME() {
				return CREATETIME;
		}

		public void setCREATETIME(Date CREATETIME) {
				this.CREATETIME = CREATETIME;
		}

		public long getNOTE_ID() {
        return NOTE_ID;
    }

    public void setNOTE_ID(long NOTE_ID) {
        this.NOTE_ID = NOTE_ID;
    }

    public Integer getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(Integer USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getCOVER_URL() {
        return COVER_URL;
    }

    public void setCOVER_URL(String COVER_URL) {
        this.COVER_URL = COVER_URL;
    }

    public Integer getVIEW_NUM() {
        return VIEW_NUM;
    }

    public void setVIEW_NUM(Integer VIEW_NUM) {
        this.VIEW_NUM = VIEW_NUM;
    }

    public Integer getLIKE_NUM() {
        return LIKE_NUM;
    }

    public void setLIKE_NUM(Integer LIKE_NUM) {
        this.LIKE_NUM = LIKE_NUM;
    }

		public Date getSTART_DAY() {
				return START_DAY;
		}

		public void setSTART_DAY(Date START_DAY) {
				this.START_DAY = START_DAY;
		}

		public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

	@Override
	public String toString() {
		return "NoteModel [NOTE_ID=" + NOTE_ID + ", USER_ID=" + USER_ID + ", TITLE=" + TITLE + ", COVER_URL="
				+ COVER_URL + ", VIEW_NUM=" + VIEW_NUM + ", LIKE_NUM=" + LIKE_NUM + ", START_DAY=" + START_DAY
				+ ", LOCATION=" + LOCATION + "]";
	}


}
