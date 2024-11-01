package com.educandoweb.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.workshopmongo.domain.User;
import com.educandoweb.workshopmongo.dto.UserDTO;
import com.educandoweb.workshopmongo.repositories.UserRepository;
import com.educandoweb.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
		
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
	
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	public void deleteById(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User obj) {
		User userDB = findById(obj.getId());
		updateData(userDB, obj);
		return userRepository.save(userDB);
	}
	
	private void updateData(User userDB, User obj) {
		userDB.setName(obj.getName());
		userDB.setEmail(obj.getEmail());
	}
	
}
