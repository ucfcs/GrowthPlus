
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

## How do I make a contribution?

By now, you should have a copy of the project on your local machine. 
If not, take a look at the README for instructions.

### Create a new branch for your issue

Find an issue/feature that you are interested in addressing.
Create a new branch for your fix, from the terminal type
`git switch -c yourNewBranchName` , this will create a new branch and run the **git checkout** command right away.

![git switch](https://github.com/ucfcs/GrowthPlus/assets/45129978/b7ab75fd-89fa-4067-b9df-39773684123f)

### Save changes to your branch

Make the appropriate changes for the issue you are trying to address or the feature that you want to add and then follow these commands:
`git status` shows you the state of the project files, this is usefull because **you do not** need to add files that Android Studio generates for your local enviroment.

![git status](https://github.com/ucfcs/GrowthPlus/assets/45129978/fc664da1-a22f-4145-b8f6-7ecf50da4f73)

`git add <file-name>` simply **stages** files so they can be committed. 

![git add](https://github.com/ucfcs/GrowthPlus/assets/45129978/2d3b26d6-332f-48b7-8cd7-4e8795ff4353)

`git commit -m "Message here"` commits your files and completely saves your progress to your branch, it is normal to have multiple commits in a branch, in fact, it is recommended to commit your progress throughout your coding session. Include a short descriptive message of your progress.

![git commit](https://github.com/ucfcs/GrowthPlus/assets/45129978/02a526ab-41b3-4adb-95b4-f09821e419c5)

`git push -u origin <branch-name>` pushes your committed changes to the remote repository, **origin** parameter will push to the repository the project was clone from, in this case, it project's copy that is in your github. 

![git push](https://github.com/ucfcs/GrowthPlus/assets/45129978/24cf23b6-6ceb-439f-97e2-cf17b3e669a1)

### Submit a Pull Request

Great Job!, if you are done with your implementation, it is time to submit a pull request. If you recently pushed your changes, look for the **Compare & pull request** green button. 

![pushing changes](https://github.com/ucfcs/GrowthPlus/assets/45129978/a997b21a-f9e7-441d-bb4c-f837f0874fa4)



### Where can I go for help?

If you need help, you can ask questions on our mailing list, IRC chat, or  [list any other communication platforms that your project uses].

