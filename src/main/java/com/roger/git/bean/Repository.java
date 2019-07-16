package com.roger.git.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model class for Repository
 */
@ApiModel(value="Repository", description="Resource on which get git repository operations are performed.")
public class Repository {
    @JsonProperty("gitusername")
    @NotNull(message = "Git username should not be empty")
    @Size(min=1, message="Git username should not be empty")
    @ApiModelProperty(notes = "Name of the GIT user",value = "gitusername",position = 0,example = "rogerdhas",dataType = "String")
    private String gitusername;

    public String getGitusername() {
        return gitusername;
    }

    public void setGitusername(String gitusername) {
        this.gitusername = gitusername;
    }
}
