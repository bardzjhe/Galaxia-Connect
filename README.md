# Galaxia-Connect
The name combines "Galaxia," a word derived from the Greek word for galaxy, and "Connect," to indicate the ability to communicate and engage with others. 

Galaxia Connect means this program is designed to connect people interested in learning about the universe in an interactive way. The software could offer features such as forums, chat rooms, educational resources, and interactive tools to enhance the learning experience. The platform could foster collaboration and encourage meaningful discussions about various aspects of the universe, from space exploration and astrophysics to the cultural and philosophical aspects of our relationship with the cosmos

Security Analysis Report URL:
https://connectpolyu.sharepoint.com/:w:/r/sites/GRP_Security_G31/Shared%20Documents/General/Security%20Analysis%20Report.docx?d=w46ec864a4d754f95a96e71e706d47df9&csf=1&web=1&e=6ZJVSI


## Steps to set up this project
1. **Clone the application**

   ```bash
   git clone https://github.com/RepublicHo/Galaxia-Connect
   ```

2. **No more MySQL. But please download Redis for caching purposes.** 
+ Without Redis, the program cannot run properly. 
For more information about how to get started on redis, please refer to [link](https://tableplus.com/blog/2018/10/how-to-start-stop-restart-redis.html).
If you are using Windows, you are suggested to download Redis-x64-5.0.14.1.msi from [link](https://github.com/tporadowski/redis/releases) 
and add redis folder to the PATH environment variables accordingly. (If you find any bugs with related to redis in the console
after running the program, it indicates that you didn't configure Redis properly). 

4. **There is no need when you test the code since by default no password needed. Configure Redis password if needed in the future.**

    + open `src/main/resources/application.properties` file.

    + change `spring.redis.password` properties as per your redis.conf file. 

5. **Run the app**

   You can run the spring boot app by typing the following command -
   
   Client:
   ```bash
   npm install (in the react-client folder)
   npm start
   ```
   
   Server:
   ```bash
   mvn spring-boot:run
   ```   
   Run the client first and then the server, enter the correct user name and password to start using.
   
   The server will start on port 8080.
