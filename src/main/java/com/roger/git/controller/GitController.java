package com.roger.git.controller;

import com.roger.git.bean.Contributors;
import com.roger.git.bean.RepoStatistics;
import com.roger.git.bean.Repository;
import com.roger.git.exception.GitException;
import com.roger.git.exception.RepoUserNotFoundException;
import com.roger.git.service.IGitApi;
import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**Rest Controller to get repositories,
 *                        number of open pr,
 *                        pr closed in current days,
 *                        number of commits in last 30 days,
 *                        number of committers in last 30 days
 *                        top 10 committers in last 30 days*/
@RestController
@Api(value = "Git Api",tags = {"GitAPI"})
@SwaggerDefinition(tags={@Tag(name="GitAPI",description = "Gets git repositories and counts based on git user")})
@RequestMapping("/api")
public class GitController {

    public static final Logger LOGGER= LogManager.getLogger(GitController.class);

    @Autowired
    IGitApi git;

    /**
     * post method to get git repositories based on gituser
     *
     * Input param Repository - git username    *
     * return - list of git repositories
     */
    @PostMapping("/repolist")
    @ApiOperation(value = "Get repositories available for a user",response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved repositories list"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
        }
    )
    public ResponseEntity<?> getAllRepos( @RequestBody @Valid Repository respository) throws GitException,RepoUserNotFoundException {
        try {
            return ResponseEntity.ok().body(git.getRepoNames(respository.getGitusername()));
        } catch (GitException ge){
            LOGGER.error("GitController :: getAllRepos GitException :: ",ge.getMessage());
            throw new GitException(ge.getMessage());
        } catch(RepoUserNotFoundException re){
            LOGGER.error("GitController :: getAllRepos RepoUserNotFoundException :: ",re.getMessage());
            throw new RepoUserNotFoundException(re.getMessage());
        }
    }

    /**
     * post method to get top 10 contributors name list in 30 days
     *
     * Input param Contributors - git username  and git repository*
     * return - list of top 10 contributors
     */
    @PostMapping("/contributorsname")
    @ApiOperation(value = "Get top 10 Contributors Names list",response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved repositories list"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    public ResponseEntity<?> getContributorsNamesList(@RequestBody @Valid Contributors contributors) throws GitException,RepoUserNotFoundException {

        try {
            return ResponseEntity.ok().body(git.getContributorsNames(contributors.getGitUserName(),contributors.getRepoName()));
        } catch (GitException ge){
            LOGGER.error("GitController :: getContributorsNamesList GitException :: ",ge.getMessage());
            throw new GitException(ge.getMessage());
        } catch(RepoUserNotFoundException re){
            LOGGER.error("GitController :: getContributorsNamesList RepoUserNotFoundException :: ",re.getMessage());
            throw new RepoUserNotFoundException(re.getMessage());
        }
    }

    /**
     * post method to get count for different operations like
     *                         number of open pr,
     *                         pr closed in current days,
     *                         number of commits in last 30 days,
     *                         number of committers in last 30 days
     *
     * Input param RepoStatistics - git username,git repository and operation type
     *                          openpr,
     *                          closedpr,
     *                          30daysCommit,
     *                          contributors
     * return - count of requested operation
     */
    @PostMapping("/statistics")
    @ApiOperation(value = "Get counts of any one of the operations ( open/closed Pr, commits in 30 days, contributors)",response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved repositories list"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "The resource you were looking for is not found")
    }
    )
    public ResponseEntity<?> getGitApiStats(@RequestBody @Valid RepoStatistics statistics) throws GitException,RepoUserNotFoundException    {
        try {
            return ResponseEntity.ok().body(git.getRepoStatistics(statistics.getGitUserName(),statistics.getRepoName(),statistics.getType()));
        } catch (GitException ge){
            LOGGER.error("GitController :: getGitApiStats GitException :: ",ge.getMessage());
            throw new GitException(ge.getMessage());
        } catch(RepoUserNotFoundException re){
            LOGGER.error("GitController :: getGitApiStats RepoUserNotFoundException :: ",re.getMessage());
            throw new RepoUserNotFoundException(re.getMessage());
        }
    }

    @GetMapping("/security")
    @ApiOperation(value = "Test api")
    public String checkUserAuth()
    {
        return "valid user";
    }

}
