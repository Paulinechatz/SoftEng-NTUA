# NTUA Software Engineering Project

## Back-end:

#### Requirements:
- MySQL
- NodeJS


#### Instructions:

1. Install dependencies
```
cd back-end
npm install
npm i nodemon
```
2. [Connect to database](back-end/routes/connect.js)

3. Set Timezone and Run
```
env TZ='Europe/London' nodemon app.js
```

#### Technologies used:

- Express
- JSON Web Tokens
- Bcryptjs

## Front-end:

#### Requirements:
- React
- NodeJS

#### Instructions:

Install dependencies
```
cd front-end
npm install
npm start
```
#### Technologies used:

- Bootstrap
- JQuery


## CLI-client:

#### Requirements:
- JDK11


#### Instructions:

1. Build project
```
cd back-end
./gradlew installDist
```

2. Run
```
./build/install/energy_017/bin/energy_017 -h
```

#### Technologies used:

- Gradle build tool
- Picocli


## Rest api client:

#### Technologies used:

Wire mock server


