package com.allegro.services;
import com.allegro.models.UserRepo;

import java.util.List;

public interface GitService {
    List<UserRepo> getUserRepos(String username) throws Exception;
}
