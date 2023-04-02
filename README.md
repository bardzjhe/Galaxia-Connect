# Galaxia-Connect
The name combines "Galaxia," a word derived from the Greek word for galaxy, and "Connect," to indicate the ability to communicate and engage with others. 

Galaxia Connect means this program is designed to connect people interested in learning about the universe in an interactive way. The software could offer features such as forums, chat rooms, educational resources, and interactive tools to enhance the learning experience. The platform could foster collaboration and encourage meaningful discussions about various aspects of the universe, from space exploration and astrophysics to the cultural and philosophical aspects of our relationship with the cosmos

Security Analysis Report URL:
https://connectpolyu.sharepoint.com/:w:/r/sites/GRP_Security_G31/Shared%20Documents/General/Security%20Analysis%20Report.docx?d=w46ec864a4d754f95a96e71e706d47df9&csf=1&web=1&e=6ZJVSI


reference: https://blog.csdn.net/allway2/article/details/122702532?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522168041294216800184110150%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=168041294216800184110150&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-11-122702532-null-null.142^v80^wechat_v2,201^v4^add_ask,239^v2^insert_chatgpt&utm_term=table%20not%20found%20h2&spm=1018.2226.3001.4187

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
   Run the client first and then the server, enter the correct auditUser name and password to start using.
   
   The server will start on port 8080.

<<<<<<< HEAD
6. Check the H2 database on  http://localhost:8080/api/h2-console
=======
Check the H2 database on http://localhost:8080/api/h2-console
>>>>>>> update
