#!/bin/bash
echo "Get the last and 3rd to last commits for a specified github repository"
echo "Enter a repository name followed by its owner."
echo ""
echo "(Example:)"
echo "Repository: acs-aem-commons"
echo "Owner     : Adobe-Consulting-Services"
echo ""

printf "Repository: " 
read repository
printf "Owner     : "  
read owner
java -jar dist/Test_Project.jar $owner $repository
