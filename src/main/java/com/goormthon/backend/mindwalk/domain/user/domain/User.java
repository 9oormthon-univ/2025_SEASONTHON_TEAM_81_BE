package com.goormthon.backend.mindwalk.domain.user.domain;

import com.goormthon.backend.mindwalk.domain.common.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "oauth_id", unique = true)
	private Long oauthId;

	@Column(name = "nickname")
	private String nickname;

	@Builder
	public User(Long oauthId) {
		this.oauthId = oauthId;
	}

	public void updateNickname(String nickname) {
		this.nickname = nickname;
	}
}
