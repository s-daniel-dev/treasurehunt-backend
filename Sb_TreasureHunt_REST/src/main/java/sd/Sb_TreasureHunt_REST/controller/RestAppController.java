package sd.Sb_TreasureHunt_REST.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sd.Sb_TreasureHunt_REST.dto.UserDto;
import sd.Sb_TreasureHunt_REST.service.AppService;



@RestController
@CrossOrigin("http://localhost:4200")
public class RestAppController {
	
	private AppService service;

	@Autowired
	public RestAppController(AppService service) {
		super();
		this.service = service;
	}


	
	@PostMapping("/game")
	public UserDto treasureHuntGame(
				Model model,
				@RequestParam("uname") String uName,
				@RequestParam("pwd") String pwd
			) {
		
		
		UserDto userDto = service.getUserDto(uName, pwd);
		
		return userDto;
	}
	
	@PostMapping("/game/restart")
	public UserDto restartGame(
				@RequestParam("name") String name,
				@RequestParam("isLost") int isLost,
				@RequestParam("count") int count
			) {
		
		UserDto userDto = service.updateUser(name, isLost, count);
		
		
		return userDto;
	}
	
}
