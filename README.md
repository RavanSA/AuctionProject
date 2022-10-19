
# Commercial Online Auction System

>Commercial Online Auction System is a mobile android application that the users can sell their items with the auction and buy items with auction. The users can create their own auctions or participate in other users' auctions and bid on the valid auction date. After the auction ends, real-time communication is established between the highest bidder and the seller.
<br/>

![Platform](https://img.shields.io/badge/Platform-Android-brightgreen.svg)
![Language](https://img.shields.io/badge/Language-Kotlin-yellowgreen.svg)
![Platform](https://img.shields.io/badge/Platform-.NET-brightgreen.svg)
![Language](https://img.shields.io/badge/Language-C%23-yellowgreen.svg)
![Database](https://img.shields.io/badge/Database-MSSQL-brightgreen.svg)
![GitHub stars](https://img.shields.io/github/stars/RavanSA/AuctionProject)
<br/>


## Prerequisites 
Before you begin, ensure you have met the following requirements:<br/>
- You have `Android Studio` installed in your machine <br/>
- You have a Android Device or Emulator with Android Version 6.0 or above. <br/>
- You should read [SRS]() documentation.<br/>


## Install Auction Project <br/>
Simply clone the repository from [here](https://github.com/RavanSA/AuctionProject/archive/refs/heads/main.zip)


## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Project Status](#project-status)
* [License](#license)

##  General Information <br/>
The proposed Commercial Online Auction System (COAS) is a mobile-based application to help users buy or sell their items. They can trade whatever they want by posting an auction. This application allows users to show their items for auction.
Auctioneers and bidders can register and bid on any available actions and create auctions. The application is developed in Kotlin on android and the user interface is developed using Jetpack Compose declarative UI Kit. The architecture of the application is made in accordance with the principles of Clean Architecture.

<br/>
<img src="https://github.com/RavanSA/AuctionProject/blob/master/preview/screens.gif" align="right" width="32%"/>

<br/>

## Technologies Used
<br/>
COAS is an android app that attempts to use the latest libraries and tools. As a summary:

- Minimum SDK level 21
- Android Studio - Arctic Fox (2020.3.1)
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- Android Jetpack library
   - Lifecycle - dispose of observing data when lifecycle state changes
   - ViewModel - UI related data holder, lifecycle aware  
   - Room Persistence - construct a database using the abstract layer
   - Navigation Component - navigate screens within the application
   - Jetpack Compose - declarative UI Kit
   - Hilt-Dagger - dependency injection
- Architecture
  - Clean Architecture (UI Layer - Domain Layer - Data Layer)
  - Repository Pattern
  - Hilt - Dagger - dependency injection
- Retrofit2 & Gson - construct the REST APIs
- OkHttp3 - implementing interceptor, logging and mocking web server
- Coil - 
- Lottie - 
- Material-Components - Material design components
- Firebase Cloud Storage
- Backend - C#, .NET, MSSQL, Swagger
- Socket, SignalR


## Features
- The users can sign up and login with Json Web Token (JWT). If token expire user need to authorize again
- The sellers can create own auction with the desired item
- The seller can specify details such as the auction's category, starting price, minimum increase, product images, auction start and end time when creating an auction
- The seller can communicate with the highest bidder with real-time messaging using SignalR Socket when the auction ends
- The seller can see past the auctions that created
- The bidder can analyze live and ended auctions
- The bidder can place a bid on auctions and see auction details	
- Users can search for auctions by sorting, filtering, and categories
- The users can add any auction to favorites and save the profile information and communication preferences


## Project Status
The project is currently under development

## License<br/>
```
MIT License

Copyright (c) [2022] [Ravan SADIGLI]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
