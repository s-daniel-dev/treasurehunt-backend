package sd.Sb_TreasureHunt_REST.dto;

public class UserDto {
	
	private String name;
	private int lifeLeft;
	private Integer record;
	
	
	public UserDto(String name, int lifeLeft, Integer record) {
		super();
		this.name = name;
		this.lifeLeft = lifeLeft;
		this.record = record;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLifeLeft() {
		return lifeLeft;
	}

	public void setLifeLeft(int lifeLeft) {
		this.lifeLeft = lifeLeft;
	}

	public Integer getRecord() {
		return record;
	}

	public void setRecord(Integer record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return "UserDto [name=" + name + ", lifeLeft=" + lifeLeft + ", record=" + record + "]";
	}
	
	

}
