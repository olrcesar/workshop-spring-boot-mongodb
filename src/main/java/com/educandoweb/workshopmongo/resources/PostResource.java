package com.educandoweb.workshopmongo.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.workshopmongo.domain.Post;
import com.educandoweb.workshopmongo.resources.util.URL;
import com.educandoweb.workshopmongo.services.PostService;


@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Post>> findById(@PathVariable String id){
		Optional<Post> post = postService.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		text = URL.decodeParam(text);
		List<Post> posts = postService.findByTitle(text);
		return ResponseEntity.ok().body(posts);
	}
	
}
