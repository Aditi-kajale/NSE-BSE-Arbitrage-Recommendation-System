# NSE-BSE-Arbitrage-Recommendation-System

Problem Statement
> You are required to construct a trade recommendation system based on Arbitrage opportunity between BSE and NSE (from Nifty stocks),
> * User logs in to system with a username and a password.
> * Once logged in, market data should be used to take the Nifty stocks and their prices on the BSE and the NSE. On the basis of the of the difference between the two prices a set of recommendations should be made where arbitrage oppurtunities exist.
> * For these stocks, use live market data (like Yahoo Finance API) and as output display key statistics alongside suggestions.
> * User can select to save any of the recommended arbitrage trades with quantity and* current market price. 
> * The data should be persisted in DB so that the data is not lost after the user closes the browser.
> * When the user logs in again the user should be able to see the saved stocks and stats.

Design Documentation

1. E-R Diagram

![11 drawio](https://user-images.githubusercontent.com/55836122/191514595-bbe0bc0b-84f7-4796-8432-98168c33ec27.png)

2. Database Schema

![image](https://user-images.githubusercontent.com/55836122/191514774-66a84b3a-72a6-42fb-8723-014d482536a3.png)

3. Flow Diagram

![111 drawio](https://user-images.githubusercontent.com/55836122/191514430-9d2d3d85-ba45-4eb5-b37d-1e9ab1b8a524.png)

4. Class Diagram

![11111](https://user-images.githubusercontent.com/55836122/191512017-bb333668-5406-41a5-9c46-b3c83533dbbb.jpg)
![1111 drawio (6)](https://user-images.githubusercontent.com/55836122/191512256-6c987eb9-d5b1-4072-b13e-a6b93b62fc3e.png)

5. End Points

![1 drawio (3)](https://user-images.githubusercontent.com/55836122/191514233-520be4f5-e8b8-4a8e-8720-40322cb4ebc2.png)

Application

1. Home Page

![image](https://user-images.githubusercontent.com/55836122/191516611-a56dbb36-3097-48b5-bb44-4932cefed81e.png)

2. Login

![image](https://user-images.githubusercontent.com/55836122/191515761-0e8021f7-a1e7-48f7-8622-6ed3835867a0.png)

3. Signup

![image](https://user-images.githubusercontent.com/55836122/191515874-48ab8101-75c3-4f3c-a1dc-db8a31d6c7df.png)

4. Dashboard: View Top 5 Recommendations (Updated every 10 secs)

![image](https://user-images.githubusercontent.com/55836122/191516103-d01e5113-2999-465c-9241-1540e83da50a.png)

5. Dashboard: Functionality to select quantity and save recommendation and also view the profit earned
![image](https://user-images.githubusercontent.com/55836122/191516800-028dd288-f037-4153-8d01-b50ee0e164d5.png)

6. Dashboard: View Live Stock Updates (Updated every 10 secs)
![image](https://user-images.githubusercontent.com/55836122/191516872-25906e44-6ed3-42b7-9e2d-1616d270bc32.png)


7. Dashboard: View Saved Recommendations (Updated every 10 secs)
![image](https://user-images.githubusercontent.com/55836122/191516958-10375320-39d5-4958-ade2-2d9de7605e4b.png)


8. Dashboard: Functionality to Delete Saved Recommendation
![image](https://user-images.githubusercontent.com/55836122/191517016-e777f539-acb6-48f2-bb76-50f8177fd8be.png)



