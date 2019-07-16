package com.roger.git.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model class for Contributors
 */
@ApiModel(value="Contributors", description="Get top 10 contributors.")
public class Contributors {

    @JsonProperty("gitusername")
    @NotNull(message = "Git username should not be empty")
    @Size(min=1, message="Git username should not be empty")
    @ApiModelProperty(notes = "Name of the GIT user",value = "gitusername",position = 0,example = "rogerdhas",dataType = "String")
    private String gitUserName;

    @JsonProperty("reponame")
    @NotNull(message = "Git repo should not be empty")
    @Size(min=1, message="Git repo should not be empty")
    @ApiModelProperty(notes = "Name of the GIT repo",value = "reponame",position = 1,example = "GitRestApi",dataType = "String")
    private String repoName;


    public String getGitUserName() {
        return gitUserName;
    }

    public void setGitUserName(String gitUserName) {
        this.gitUserName = gitUserName;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }
}
