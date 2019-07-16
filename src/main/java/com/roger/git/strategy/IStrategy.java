package com.roger.git.strategy;

import com.roger.git.exception.GitException;

public interface IStrategy {
    public String createGitApiUrl(String gitUser,String repoName,String type) throws GitException;
}
