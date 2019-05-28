package com.caoliyuan.travelGuide.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteItemModel {
    /**
     * 日记项id
     */
    private Integer NOTE_ITEM_ID;

    /**
     * 日记id
     */
    private long NOTE_ID;

    /**
     * 第几天
     */
    private Integer DAY_NUM;

    /**
     * 图片url
     */
    private String IMG_URL;

    /**
     * 内容
     */
    private String CONTENT;

    /**
     * 时间
     */
    private String TIME;

    /**
     * 地点
     */
    private String LOCATION;

    public static RowMapper<NoteItemModel> noteItemMapper = new RowMapper<NoteItemModel>() {
			@Override
			public NoteItemModel mapRow(ResultSet resultSet, int i) throws SQLException {
					return new NoteItemModel(
							resultSet.getInt("note_item_id"),
							resultSet.getLong("note_id"),
							resultSet.getInt("day_num"),
							resultSet.getString("img_url"),
							resultSet.getString("content"),
							resultSet.getString("time"),
							resultSet.getString("location")
					);
			}
	};


	public NoteItemModel(Integer NOTE_ITEM_ID, long NOTE_ID, Integer DAY_NUM, String IMG_URL, String CONTENT, String TIME, String LOCATION) {

		this.NOTE_ITEM_ID = NOTE_ITEM_ID;
		this.NOTE_ID = NOTE_ID;
		this.DAY_NUM = DAY_NUM;
		this.IMG_URL = IMG_URL;
		this.CONTENT = CONTENT;
		this.TIME = TIME;
		this.LOCATION = LOCATION;
	}

	public NoteItemModel() {}
	
	public Integer getNOTE_ITEM_ID() {
		return NOTE_ITEM_ID;
	}

	public void setNOTE_ITEM_ID(Integer nOTE_ITEM_ID) {
		NOTE_ITEM_ID = nOTE_ITEM_ID;
	}

	public long getNOTE_ID() {
		return NOTE_ID;
	}

	public void setNOTE_ID(long nOTE_ID) {
		NOTE_ID = nOTE_ID;
	}

	public Integer getDAY_NUM() {
		return DAY_NUM;
	}

	public void setDAY_NUM(Integer DAY_NUM) {
		DAY_NUM = DAY_NUM;
	}

	public String getIMG_URL() {
		return IMG_URL;
	}

	public void setIMG_URL(String iMG_URL) {
		IMG_URL = iMG_URL;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}

	public String getTIME() {
		return TIME;
	}

	public void setTIME(String tIME) {
		TIME = tIME;
	}

	public String getLOCATION() {
		return LOCATION;
	}

	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}
}
