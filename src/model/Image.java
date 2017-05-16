package model;

import java.util.Date;

public class Image {

	private Long id;
	private String path;
	private Date date;
	private Long cameraId;
	

	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Long getCameraId() {
		return cameraId;
	}
	
	public void setCameraId(Long cameraId) {
		this.cameraId = cameraId;
	}
}
