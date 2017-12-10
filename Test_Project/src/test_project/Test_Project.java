/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_project;

import java.io.IOException;
import java.util.LinkedList;


import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.NoSuchPageException;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.service.CommitService;
 
public class Test_Project {
    
   public static RepositoryCommit commitsFromMostRecent(LinkedList<RepositoryCommit> commitList, int fromMostRecent)
   {
       return commitList.get(commitList.size()- fromMostRecent);
   }
    public static void main(String[] args) throws IOException  {
        //Most recent commit is 1
        final int recentCommit = 1;
        final int anotherRecentCommit = 3;
        String repositoryOwner = args[0];
        String repositoryName = args[1];
        String clientUserName = "mschwartz-test";
        String clientUserPassword = "passw0rd";
        
        GitHubClient client = new GitHubClient();       
        client.setCredentials(clientUserName,clientUserPassword);             
        CommitService service = new CommitService(client);
        RepositoryId repId = new RepositoryId(repositoryOwner,repositoryName);  
       
       try {
           PageIterator<RepositoryCommit> pgCommits = service.pageCommits(repId);
            LinkedList<RepositoryCommit> commitList = new LinkedList<>();
       
        for (RepositoryCommit r : pgCommits.next())            
                commitList.push(r);
            
            RepositoryCommit lastCommit = commitsFromMostRecent(commitList,recentCommit);
            RepositoryCommit thirdToLastCommit = commitsFromMostRecent(commitList,anotherRecentCommit);
            FormatJson formattedJson = new FormatJson(lastCommit, thirdToLastCommit);
       
            System.out.println(formattedJson.getFormattedString());
       }
       catch (NoSuchPageException e){
           System.out.println("That repository doesnt exist. Please try again.");
           System.exit(1);
           
       }
       
                                         
    
    }
}