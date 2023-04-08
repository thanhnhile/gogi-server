package vn.com.gigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.services.impl.AccountServiceImpl;

@RestController
@RequestMapping("/like")
public class LikeController {
	@Autowired
	private AccountServiceImpl accountImpl;
	
	@PostMapping("/{id}")
	public DataResponse likeProduct(@PathVariable(value = "id") Long id) {
		return new DataResponse(accountImpl.likeProduct(id));
	}

	@PostMapping("/unlike/{id}")
	public DataResponse unlikeProduct(@PathVariable(value = "id") Long id) {
		return new DataResponse(accountImpl.unlikeProduct(id));
	}
}
