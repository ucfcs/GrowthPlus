# Contributor guidelines 

Thank you for investing your time in contributing to our project! 
Please read our [Code of Conduct](./CODE_OF_CONDUCT.md) to keep our community approachable and respectable.

## What do I need to know to help?
If you are looking to help to with a code contribution, our project uses  Android as our OS, Realm as our local database, Java (_Kotlin in the near future_) as our developing language and Git for collaboration. If you don't feel ready to make a code contribution yet, no problem! You can help us out with  documentation issues or the design issues.
If you are interested in making a code contribution and would like to learn more about the technologies that we use, check out the list below.

* [Realm](https://www.mongodb.com/docs/realm/sdk/java/realm-database/) - Realm is a fast, scalable alternative to SQLite for mobile development. 
* [Android](https://developer.android.com/guide) - The operating system inside 2.5 billion active devices.
* [Java SE](https://docs.oracle.com/en/java/) - Java SE and component technologies offer the rich user interface, performance, versatility, portability, and security.
* [Git](https://git-scm.com/docs) - Git is easy to learn and has a tiny footprint with lightning fast performance..

## Collaboration workflow

By now, you should have a copy of the project on your local machine. 
If not, take a look at the README for instructions.
> This quick rundown introduces Git and the command line to beginners. If you are an experienced collaborator/programmer, more power to you!, you can skip this part and head over to contribution guidelines. 
 
Before moving on, open the terminal and type the command `git remote add upstream https://github.com/ucfcs/GrowthPlus.git` to track the original project.

![add upstream](https://github.com/ucfcs/GrowthPlus/assets/45129978/8493291e-3c7a-4094-b001-2cbadc131e09)

### Create a new branch for your issue

Find an issue/feature that you are interested in addressing.
Create a new branch for your fix, from the terminal type
`git switch -c yourNewBranchName` , this will create a new branch and run the **git checkout** command right away.

![git switch](https://github.com/ucfcs/GrowthPlus/assets/45129978/86ee504f-47b1-4e18-9039-fb735bdae025)

### Save changes to your branch

Make the appropriate changes for the issue you are trying to address or the feature that you want to add and then follow these commands:
`git status` shows you the state of the project files, this is usefull because **you do not** need to add files that Android Studio generates for your local enviroment.

![git status](https://github.com/ucfcs/GrowthPlus/assets/45129978/caa6ea32-d80c-4b19-b372-6629d912822e)

`git add <file-name>` simply **stages** files so they can be committed. 

![git add](https://github.com/ucfcs/GrowthPlus/assets/45129978/08b08f36-8be1-40bf-9574-71c815798622)

`git commit -m "Message here"` commits your files and completely saves your progress to your branch, it is normal to have multiple commits in a branch, in fact, it is recommended to commit your progress throughout your coding session. Include a short descriptive message of your progress.

![git commit](https://github.com/ucfcs/GrowthPlus/assets/45129978/4e4b7dfe-641e-4382-ac7d-1366fe8f732f)

`git push -u origin <branch-name>` pushes your committed changes to the remote repository, **origin** parameter will push to the repository the project was clone from, in this case, it's the project's copy that is in your github. 

![git push](https://github.com/ucfcs/GrowthPlus/assets/45129978/fd878b55-1ee5-47c3-a02f-557e76e03220)

### Sync the forked project

Before submitting a pull request, make sure your forked copy is to up date with the original project. Fetch the lastest changes with `git fetch upstream`

![git fecth upstream](https://github.com/ucfcs/GrowthPlus/assets/45129978/8539801c-ab22-48c7-ad13-617b7cff1881)

`git checkout main` to go back to the main branch in your local repo. 

![checkout to main](https://github.com/ucfcs/GrowthPlus/assets/45129978/1a214141-56d7-44ff-840d-10ef890fd7c5)

`git merge upstream/main`, merges the changes from upstream/main branch to your local main branch, this brings your fork's main branch into sync with the upstream repository, without losing your local changes.

![git merge upstream main](https://github.com/ucfcs/GrowthPlus/assets/45129978/97887bd6-2d15-4306-9465-715a1aa8f6ce)

In the local main branch, `git push` updates your remote forked copy.
Go back to the working branch, `git checkout <branch-name>`

### Merge your branch with the main branch 

`git merge main` combines the changes of the main branch and your working branch, this is why we update the main branch with the latest changes before merging with your branch's progress, you must ensure that your working branch is up to date to avoid merge conflicts when doing a pull request.

![git merge main](https://github.com/ucfcs/GrowthPlus/assets/45129978/bfc69cfd-4e25-41f6-8d7b-3654723901f1)

## Submit a Pull Request and guidelines

Great Job!, if you are done with your implementation, it is time to submit a pull request. If you recently pushed your changes to your own forked repository, click the **Compare & pull request** green button. 

![compare and pull request](https://github.com/ucfcs/GrowthPlus/assets/45129978/c9b82bba-9f3d-4db1-99c8-5e16e5c28e1b)

In the **Write** tab: 
- Include a brief description about your pull request, what issue or feature are addressing and how you fixed it. 
- Provide testing instruction, what should the reviewers be looking for? , be as detail as possible. 
- Include the following script `ucfcs/GrowthPlus #<issue-number>` at the very end, this will refer and close the specific issue you were working on.
- Finally, click **Create pull request**

![open PR](https://github.com/ucfcs/GrowthPlus/assets/45129978/dc78ae5c-f0ed-4fbf-8bf9-5960d5939614)

What happends now?, each pull request needs two approvals from maintainer. 
If a pull request does not pass the testing steps or does not function as it should, it is the responsibility of the reviewer to document the issues and it is the responsibility of the owner of the pull request to fix it and push new changes.
  
### Where can I go for help?

If you need help, you can ask questions on our mailing list, contactarroseplus@gmail.com.
If you need help with programming or are stuck somewhere, write a comment in the issue that you are working, a maintener will try to address it as soon as possible. 

