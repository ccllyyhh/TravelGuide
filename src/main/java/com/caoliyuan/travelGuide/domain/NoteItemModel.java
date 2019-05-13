package com.caoliyuan.travelGuide.domain;

public class NoteItemModel {
    /**
     * 日记项id
     */
    private Integer NOTE_ITEM_ID;

    /**
     * 日记id
     */
    private Integer NOTE_ID;

    /**
     * index
     */
    private Integer COUNT;

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

	public NoteItemModel(Integer NOTE_ITEM_ID, Integer NOTE_ID, Integer COUNT, String IMG_URL, String CONTENT, String TIME, String LOCATION) {

		this.NOTE_ITEM_ID = NOTE_ITEM_ID;
		this.NOTE_ID = NOTE_ID;
		this.COUNT = COUNT;
		this.IMG_URL = IMG_URL;
		this.CONTENT = CONTENT;
		this.TIME = TIME;
		this.LOCATION = LOCATION;
	}

	public NoteItemModel(Integer NOTE_ID, Integer COUNT, String IMG_URL, String CONTENT, String TIME, String LOCATION) {
		this.NOTE_ID = NOTE_ID;
		this.COUNT = COUNT;
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

	public Integer getNOTE_ID() {
		return NOTE_ID;
	}

	public void setNOTE_ID(Integer nOTE_ID) {
		NOTE_ID = nOTE_ID;
	}

	public Integer getCOUNT() {
		return COUNT;
	}

	public void setCOUNT(Integer cOUNT) {
		COUNT = cOUNT;
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
