# QuizApp-Java

An application for exam managment.

---

## Video Output:



https://user-images.githubusercontent.com/55599023/220412763-49ba87d6-8c1c-477a-8e97-cc5fd34c702b.mp4



## Scenerio
1. Run the application from netbeans
2. Login as admin (username: admin	password: admin)
3. Add a student (username: alex	password: alex)
4. Add a question
5. View marks of all student
6. close and run the app again and login as student this time
7. participate in exam
8. you will see marks at the end of exam

## How to run this project
- download Netbeans (8.2 or greater version)
- clone this repo and open it from netbeans using File->Open Project
- press the green run button

## Technology used:
- Java 8
- Ant build tools
- Netbeans 8.2

## Admin Features:
  - Admin can add students
  - Add Questions to question bank
  - View marks of student
  
## Student Features:
  - Can participate in exam
  - View result instantly after exam
  
## Screenshots (Admin UI):
![1](https://github.com/zafir100100/QuizApp-Java/blob/master/resources/screenshots/1.png)
![2](https://github.com/zafir100100/QuizApp-Java/blob/master/resources/screenshots/2.png)
![3](https://github.com/zafir100100/QuizApp-Java/blob/master/resources/screenshots/3.png)
![4](https://github.com/zafir100100/QuizApp-Java/blob/master/resources/screenshots/4.png)
![5](https://github.com/zafir100100/QuizApp-Java/blob/master/resources/screenshots/5.png)

## Screenshots (Student UI):
![6](https://github.com/zafir100100/QuizApp-Java/blob/master/resources/screenshots/6.png)
![7](https://github.com/zafir100100/QuizApp-Java/blob/master/resources/screenshots/7.png)
![8](https://github.com/zafir100100/QuizApp-Java/blob/master/resources/screenshots/8.png)
![9](https://github.com/zafir100100/QuizApp-Java/blob/master/resources/screenshots/9.png)

## Admin credential:
  - default username: admin
  - default password: admin

## Student credential:
  - You can change student credential from resources/databases/users.json
  - default username: a
  - default password: 1

## Questions
- Question are located in resources/databases/questions.json

## Marks
- Marks are located in resources/databases/users.json

## Major Issues
- Test score is not showing in UI and not updated in json if don't use jdk 8 and netbeans 8.2 
  (Apache Netbeans <= 16 seems ok but might generate issues)
