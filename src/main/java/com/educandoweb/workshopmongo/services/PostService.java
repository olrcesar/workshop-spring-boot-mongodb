package com.educandoweb.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.workshopmongo.domain.Post;
import com.educandoweb.workshopmongo.repositories.PostRepository;
import com.educandoweb.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Optional<Post> findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		if(post == null) {
			throw new ObjectNotFoundException("Objeto não encontrado.");
		}
		return post;
	}
	
}
