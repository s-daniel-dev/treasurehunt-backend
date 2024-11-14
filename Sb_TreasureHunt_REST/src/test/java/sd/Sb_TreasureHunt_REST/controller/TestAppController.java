package sd.Sb_TreasureHunt_REST.controller;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import sd.Sb_TreasureHunt_REST.dto.UserDto;
import sd.Sb_TreasureHunt_REST.service.AppService;

@WebMvcTest
public class TestAppController {
	
	@Autowired
	@MockBean
	private AppService service;
	
	@Autowired
	private MockMvc mockito;
	
	@Test
	public void testTreasureHuntGame() throws Exception {
		
		final String name = "Dani";
		final String pwd = "pwd";
		final int lifeLeft = 3;
		final int record = 4;
		UserDto userDto = new UserDto(name, lifeLeft, record);
		
		BDDMockito.given(service.getUserDto(name, pwd)).willReturn(userDto);
		
		mockito.perform(MockMvcRequestBuilders.post("/game").param("uname", String.valueOf(name)).param("pwd", String.valueOf(pwd)).contentType(MediaType.APPLICATION_JSON))
		
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(userDto.getName())))
	        .andExpect(MockMvcResultMatchers.jsonPath("$.lifeLeft", CoreMatchers.is(userDto.getLifeLeft())))
			.andExpect(MockMvcResultMatchers.jsonPath("$.record", CoreMatchers.is(userDto.getRecord())));
		
	}
	
	@Test
	public void testRestartGame() throws Exception {
		
		final String name = "name";
		final int isLost = 1;
		final int count = 6;
		final int lifeLeft = 3;
		UserDto userDto = new UserDto(name, lifeLeft, count);
		
		BDDMockito.given(service.updateUser(name, isLost, count)).willReturn(userDto);
		
		mockito.perform(MockMvcRequestBuilders.post("/game/restart").param("name", name).param("isLost", String.valueOf(isLost)).param("count", String.valueOf(count)).contentType(MediaType.APPLICATION_JSON))
		
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(userDto.getName())))
			.andExpect(MockMvcResultMatchers.jsonPath("$.lifeLeft", CoreMatchers.is(userDto.getLifeLeft())))
			.andExpect(MockMvcResultMatchers.jsonPath("$.record", CoreMatchers.is(userDto.getRecord())));
		
		
	}

}































