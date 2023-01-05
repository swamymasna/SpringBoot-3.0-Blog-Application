package com.swamy.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "post_tbl", uniqueConstraints = @UniqueConstraint(columnNames = { "title" }))
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "content")
	private String content;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comment>comments = new HashSet<>();
}

