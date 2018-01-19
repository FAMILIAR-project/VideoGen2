package com.videogen.myapp.web.rest;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;


/**
 * Controller for view and managing Log Level at runtime.
 */
@RestController
@RequestMapping("/api")
public class VideoResource {
	
	static final String content = "src/main/webapp/content/folder/";


	@GetMapping("/random")
    @Timed
    @Produces({ MediaType.TEXT_PLAIN })
	public Response GetRandom() {
		main.Main.clean(content);
		main.Main.random(content);
		
        return Response.ok("random created!").build();
	}
	
	@GetMapping("/custom")
    @Timed
    @Produces({ MediaType.TEXT_PLAIN })
	public Response GetCustom() {
        return Response.ok("custom created!").build();
	}

	@GetMapping("/gif")
    @Timed
    @Produces({ MediaType.TEXT_PLAIN })
	public Response GetGif() {
		main.Main.export(content);
        return Response.ok("gif created!").build();
	}
	
	@GetMapping("/vignette")
    @Timed
    @Produces({ MediaType.TEXT_PLAIN })
	public Response GetVignette() {
        return Response.ok("Vignette created!").build();
	}
	
//	public static void main(String[] args) {
//		
//		
//		System.out.println(util.Utils.isFileExist("generator.jar"));
//
//	}
}
