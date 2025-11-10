package com.los_pitufs.los_pitufs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.los_pitufs.los_pitufs.model.Follow;
import com.los_pitufs.los_pitufs.model.User;
import com.los_pitufs.los_pitufs.repository.FollowRepository;
import com.los_pitufs.los_pitufs.repository.UserRepository;

@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public void followUser(Long followerId, Long followedId) {
        if (followerId.equals(followedId)) {
            throw new IllegalArgumentException("Um usuário não pode seguir a si mesmo.");
        }

        User follower = userRepository.findById(followerId)
            .orElseThrow(() -> new RuntimeException("Usuário seguidor não encontrado."));

        User followed = userRepository.findById(followedId)
            .orElseThrow(() -> new RuntimeException("Usuário a ser seguido não encontrado."));

        followRepository.findByFollowerAndFollowed(follower, followed)
            .ifPresent(f -> { throw new RuntimeException("Usuário já está seguindo este perfil."); });

        Follow follow = Follow.builder()
            .follower(follower)
            .followed(followed)
            .build();

        followRepository.save(follow);
    }

    public void unfollowUser(Long followerId, Long followedId) {
        User follower = userRepository.findById(followerId)
            .orElseThrow(() -> new RuntimeException("Usuário seguidor não encontrado."));
        User followed = userRepository.findById(followedId)
            .orElseThrow(() -> new RuntimeException("Usuário a ser seguido não encontrado."));

        Follow follow = followRepository.findByFollowerAndFollowed(follower, followed)
            .orElseThrow(() -> new RuntimeException("Usuário não está seguindo este perfil."));

        followRepository.delete(follow);
    }

    public List<Follow> getFollowers(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return followRepository.findByFollowed(user);
    }

    public List<Follow> getFollowing(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return followRepository.findByFollower(user);
    }
}
