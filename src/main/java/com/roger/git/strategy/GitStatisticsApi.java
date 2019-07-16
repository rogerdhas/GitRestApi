package com.roger.git.strategy;

import com.roger.git.exception.GitException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.roger.git.util.GitConstant.*;
import static com.roger.git.util.GitConstant.INVALID_URL;


/**
 * Returns the appropriate git api endpoint
 */
public class GitStatisticsApi implements IStrategy {


    @Override
    public String createGitApiUrl(String gitUser,String repoName,String type) throws GitException {

        DateTimeFormatter dateFormatter=  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String url = GIT_REPO_DETAILS.replace(REP_USR_NME, gitUser).replace(REP_NME,repoName);
        if(type.equalsIgnoreCase(PR_STATUS_OPEN)) {
            url += PR_OPEN_DETAILS;
        }
        else if(type.equalsIgnoreCase(PR_STATUS_CLOSED))
        {//new Date()
            url += PR_CLOSED_DETAILS + dateFormatter.format(LocalDate.now());
        }
        else if (type.equalsIgnoreCase(COMMIT_HISTORY))
        {
            url +=  COMMIT_DETAILS + dateFormatter.format(LocalDate.now().minusDays(30));
        }
        else if (type.equalsIgnoreCase(CONTRIBUTORS))
        {
            url +=  CONTRIBUTORS_DETAILS + dateFormatter.format(LocalDate.now().minusDays(30));
        }
        else
        {
            throw new GitException(INVALID_URL);
        }
        return url;
    }
}
