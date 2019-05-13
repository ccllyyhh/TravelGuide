package com.caoliyuan.travelGuide.domain;

public class NoteModel {
    /**
     * 日记id
     */
    private Integer NOTE_ID;

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
    private String START_DAY;

    /**
     * 地点
     */
    private String LOCATION;

    public NoteModel() {
    	
    }
    
    
    public NoteModel(Integer uSER_ID, String tITLE, String cOVER_URL, String sTART_DAY, String lOCATION) {
		super();
		USER_ID = uSER_ID;
		TITLE = tITLE;
		COVER_URL = cOVER_URL;
		START_DAY = sTART_DAY;
		LOCATION = lOCATION;
	}


	public NoteModel(Integer nOTE_ID, Integer uSER_ID, String tITLE, String cOVER_URL, Integer vIEW_NUM,
			Integer lIKE_NUM, String sTART_DAY, String lOCATION) {
		super();
		NOTE_ID = nOTE_ID;
		USER_ID = uSER_ID;
		TITLE = tITLE;
		COVER_URL = cOVER_URL;
		VIEW_NUM = vIEW_NUM;
		LIKE_NUM = lIKE_NUM;
		START_DAY = sTART_DAY;
		LOCATION = lOCATION;
	}

	public Integer getNOTE_ID() {
        return NOTE_ID;
    }

    public void setNOTE_ID(Integer NOTE_ID) {
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

    public String getSTART_DAY() {
        return START_DAY;
    }

    public void setSTART_DAY(String START_DAY) {
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
