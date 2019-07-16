package com.roger.git.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model class for Statistics
 */
@ApiModel(value="Statistics", description="Resource on which get git statistics operations are performed.")
public class RepoStatistics {

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

        @JsonProperty("type")
        @NotNull(message = "Git operation should not be empty")
        @Size(min=1, message="Git operation should not be empty")
        @ApiModelProperty(notes = "Name of the GIT operation like openpr,closedpr,30daysCommit,contributors",value = "type",position = 2,example = "openpr",dataType = "String")
        private String type;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
