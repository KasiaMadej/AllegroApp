package com.allegro.controllers;

import com.allegro.exceptions.UserNotFoundException;
import com.allegro.models.Search;
import com.allegro.models.UserRepo;
import com.allegro.services.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@Controller
public class GitController {
    private GitService gitService;

    @Autowired
    public GitController(GitService gitService) {
        this.gitService = gitService;
    }

    @GetMapping("/searchRepo")
    public String searchForm(Model model) {
        model.addAttribute("search", new Search());
        return "searchRepo";
    }

    @PostMapping("/gitRepos")
    public String getUserRepos(Model model, @ModelAttribute Search search) {
        try {
            List<UserRepo> userReposList = gitService.getUserRepos(search.getUsername());
            model.addAttribute("userRepos", userReposList);
            model.addAttribute("allStars", userReposList.stream().mapToInt(UserRepo::getStargazersCount).sum());
            model.addAttribute("username", search.getUsername());
        } catch (UserNotFoundException e) {
            model.addAttribute("error", "User Not Found");
            return "error";
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                model.addAttribute("error", "Api call internal error");
            } else if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                model.addAttribute("error", "Api call error - limit exceeded");
            }
            return "error";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        return "gitRepos";

    }
}
