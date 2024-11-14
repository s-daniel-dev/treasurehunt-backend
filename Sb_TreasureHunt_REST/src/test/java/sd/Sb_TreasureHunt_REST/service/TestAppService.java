package sd.Sb_TreasureHunt_REST.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import sd.Sb_TreasureHunt_REST.db.Database;
import sd.Sb_TreasureHunt_REST.dto.UserDto;
import sd.Sb_TreasureHunt_REST.model.User;

@SpringBootTest
public class TestAppService {
	
	@Autowired
	@MockBean
	private Database db;
	
	@Autowired
	private AppService service;
	
	@Test
	public void getUserDtoTest() {
		
		final int id = 0;
		final String name = "name";
		final String pwd = "pdw";
		final int lifeLeft = 3;
		final Integer record = null;
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setPwd(pwd);
		user.setLifeLeft(lifeLeft);
		user.setRecord(record);
		
		BDDMockito.given(db.getUserByNameAndPwd(name, pwd)).willReturn(user);
		
		UserDto userDto = service.getUserDto(name, pwd);
		
		Assertions.assertEquals(name, userDto.getName());
		Assertions.assertEquals(lifeLeft, userDto.getLifeLeft());
		Assertions.assertEquals(record, userDto.getRecord());
	}
	
	@Test
	public void updateUserLoseTest() {
		
		final int id = 0;
		final String name = "name";
		final int isLost = 1;
		final int count = 5;
		final int lifeLeft = 3;
		final String pwd = "pdw";
		final Integer record = null;
		
		User user = new User();
		user.setId(id);
		user.setLifeLeft(lifeLeft);
		user.setName(name);
		user.setRecord(record);
		user.setPwd(pwd);
		
		BDDMockito.given(db.getUserByName(name)).willReturn(user);
		
		
		UserDto dto = service.updateUser(name, isLost, count);
		
		Assertions.assertEquals(name, dto.getName());
		Assertions.assertEquals(lifeLeft - 1, dto.getLifeLeft());
		Assertions.assertEquals(record, dto.getRecord());
		
		
		
	}
	
	@Test
	public void updateUserWonWithRecordTest() {
		
		final int id = 0;
		final String name = "name";
		final int isLost = 0;
		final int count = 5;
		final int lifeLeft = 3;
		final String pwd = "pdw";
		final Integer record = null;
		
		User user = new User();
		user.setId(id);
		user.setLifeLeft(lifeLeft);
		user.setName(name);
		user.setRecord(record);
		user.setPwd(pwd);
		
		BDDMockito.given(db.getUserByName(name)).willReturn(user);
		
		
		UserDto dto = service.updateUser(name, isLost, count);
		
		Assertions.assertEquals(name, dto.getName());
		Assertions.assertEquals(lifeLeft, dto.getLifeLeft());
		Assertions.assertEquals(count, dto.getRecord());
		
	}
	
	@Test
	public void updateUserWonWithNoRecordTest() {
		
		final int id = 0;
		final String name = "name";
		final int isLost = 0;
		final int count = 5;
		final int lifeLeft = 3;
		final String pwd = "pdw";
		final Integer record = 4;
		
		User user = new User();
		user.setId(id);
		user.setLifeLeft(lifeLeft);
		user.setName(name);
		user.setRecord(record);
		user.setPwd(pwd);
		
		BDDMockito.given(db.getUserByName(name)).willReturn(user);
		
		UserDto dto = service.updateUser(name, isLost, count);
		
		Assertions.assertEquals(name, dto.getName());
		Assertions.assertEquals(lifeLeft, dto.getLifeLeft());
		Assertions.assertEquals(record, dto.getRecord());
		
	}
	
	@Test
	public void testUpdateUserWonWithRecord() {
		
		final int id = 0;
		final String name = "name";
		final String pwd = "pdw";
		final int lifeLeft = 3;
		final Integer record = 10;
		final int count = 3;
		final int isLost = 0;
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setLifeLeft(lifeLeft);
		user.setPwd(pwd);
		user.setRecord(record);
		
		BDDMockito.given(db.getUserByName(name)).willReturn(user);
		
		UserDto userDto = service.updateUser(name, isLost, count);
		
		Assertions.assertEquals(name, userDto.getName());
		Assertions.assertEquals(lifeLeft, userDto.getLifeLeft());
		Assertions.assertEquals(count, userDto.getRecord());
		
	}
	
	@Test
	public void updateUserNullTest() {
		
		final String name = "name";
		final int count = 0;
		final int isLost = 1;
		
		BDDMockito.given(db.getUserByName(name)).willReturn(null);
		
		UserDto userDto = service.updateUser(name, isLost, count);
		
		Assertions.assertNull(userDto);
		
		
	}
}

















