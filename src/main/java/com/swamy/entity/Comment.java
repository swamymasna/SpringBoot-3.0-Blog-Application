package com.swamy.entity;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "comment_tbl", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "body")
	private String body;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
}
