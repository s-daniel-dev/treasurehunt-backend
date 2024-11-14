package sd.Sb_TreasureHunt_REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sd.Sb_TreasureHunt_REST.db.Database;
import sd.Sb_TreasureHunt_REST.dto.UserDto;
import sd.Sb_TreasureHunt_REST.model.User;


@Service
public class AppService {
	
	private Database db;
	
	
	@Autowired
	public AppService(Database db) {
		this.db = db;
	}

	

	public UserDto getUserDto(String uName, String pwd) {

		User user = db.getUserByNameAndPwd(uName, pwd);
		
		UserDto userDto = convertUserToDto(user);
		
		return userDto;
	}



	private UserDto convertUserToDto(User user) {

		UserDto userDto = null;
		
		if(user != null) {
			
			userDto = new UserDto(
						user.getName(),
						user.getLifeLeft(),
						user.getRecord()
					);
			
		}
		return userDto;
	}



	public UserDto updateUser(String name, int isLost, int count) {

		User user = db.getUserByName(name);
		
		if(user != null) {
			
			if(isLost == 1) {
				user.setLifeLeft(user.getLifeLeft() - 1);
			}
			else if(isLost != 1 && (user.getRecord() == null || user.getRecord() > count)) {
				user.setRecord(count);
			}
			
			db.mergeUser(user);
		}
		
		
		UserDto userDto = convertUserToDto(user);
		
		return userDto;
	}




}
