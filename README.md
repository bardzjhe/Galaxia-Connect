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

2. **Create MySQL database**

   ```bash
   create database galaxia_connect_db
   ```
3. **Change MySQL username and password as per your MySQL installation**

    + open `src/main/resources/application.properties` file.

    + change `spring.datasource.username` and `spring.datasource.password` properties as per your mysql installation

4. **Run the app**

   You can run the spring boot app by typing the following command -

   ```bash
   mvn spring-boot:run
   ```

   The server will start on port 8080.
