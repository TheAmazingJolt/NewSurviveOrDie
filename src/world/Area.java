package world;

public class Area {

	private String areaFileName;
	
	private String areaName;
	private int areaId;
	
	public Area(String areaName, int areaId, String areaFileName) {
		this.areaName = areaName;
		this.areaId = areaId;
		this.areaFileName = areaFileName;
	}

	public String getAreaFileName() {
		return areaFileName;
	}

	public void setAreaFileName(String areaFileName) {
		this.areaFileName = areaFileName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	
	
	
}
