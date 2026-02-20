package com.CircleUp.CircleUp.controller;

import com.CircleUp.CircleUp.model.Follow;
import com.CircleUp.CircleUp.model.Post;
import com.CircleUp.CircleUp.model.User;
import com.CircleUp.CircleUp.repository.FollowRepository;
import com.CircleUp.CircleUp.repository.PostRepository;
import com.CircleUp.CircleUp.repository.UserRepository;
import com.CircleUp.CircleUp.service.FeedService;
import com.CircleUp.CircleUp.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FollowService followService;
    private final PostRepository postRepository;
    private final FeedService feedService;
    @GetMapping
    public List<Post> getFeed(@AuthenticationPrincipal User user) {

        return feedService.getFeed(user.getId());
    }
}
