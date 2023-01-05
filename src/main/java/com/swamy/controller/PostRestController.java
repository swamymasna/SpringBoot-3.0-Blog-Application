package com.swamy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swamy.payload.PostDTO;
import com.swamy.payload.PostResponse;
import com.swamy.service.IPostService;
import com.swamy.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {

	@Autowired
	private IPostService postService;

	@PostMapping("/save")
	public ResponseEntity<PostDTO> savePost(@Valid @RequestBody PostDTO postDTO) {
		return new ResponseEntity<>(postService.savePost(postDTO), HttpStatus.CREATED);
	}

	@GetMapping("/list")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy) {

		return ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize, sortBy));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPostByIdV1(@PathVariable Integer id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(postService.updatePost(postDTO, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Integer id) {
		postService.deletePost(id);
		return new ResponseEntity<String>("Post Deleted Successfully", HttpStatus.OK);
	}
}
