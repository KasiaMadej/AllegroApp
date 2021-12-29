package com.allegro.services;

import com.allegro.dto.UserRepoDto;
import com.allegro.exceptions.UserNotFoundException;
import com.allegro.models.UserRepo;
import com.allegro.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GitServiceImpl implements GitService {

    @Override
    public List<UserRepo> getUserRepos(String username) throws Exception {
        final String uri = Constants.PATH_URL + username + "/repos";

        RestTemplate restTemplate = new RestTemplate();
        List<UserRepoDto> userReposDto;

        try {
            ResponseEntity<UserRepoDto[]> responseEntity = restTemplate.getForEntity(uri, UserRepoDto[].class);

            userReposDto = Arrays.asList(responseEntity.getBody());

            if (userReposDto.size() == 0) {
                return null;
            }
            return userReposDto.stream().map(userRepoDto -> {
                return new UserRepo(userRepoDto.getName(), userRepoDto.getStargazersCount(), getUserRepoLanguages(userRepoDto, restTemplate));
            }).collect(Collectors.toList());

        } catch (UserNotFoundException e) {
            throw e;
        }
    }

    private HashMap<String, Integer> getUserRepoLanguages(UserRepoDto userRepoDto, RestTemplate restTemplate) {
        ResponseEntity<Map> responseMapEntity = restTemplate.getForEntity(userRepoDto.getLanguageUrl(), Map.class);
        return new HashMap<String, Integer>(responseMapEntity.getBody());
    }
}
