# _Wildlife Tracker_
[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg)](https://github.com/RichardLitt/standard-readme)

### Description
> Application for the Forest Service to track animals during an environmental impact study.

Project which I am provided with the code containing the basic classes, Spark setup, VelocityTemplateEngine, vtl template structure, routing in App.java, tests, and database setup instructions, which I am to test, correct errors, and complete the requirements for.

The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. This application was developed to allow Rangers to track wildlife sightings in the area.

## Table of Contents
- [Sections](#sections)
    - [Install](#install)
    - [Installation Requirements](#setup/insallation-requirements)
    - [Manual Database Creation](#manual-database-creation)
    - [Usage](#usage)
    - [Screen Shots](#screen-shots)
    - [Known Bugs](#known-bugs)
    - [Sources](#sources)
    - [Maintainer](#maintainer)
    - [Contribute](#contribute)
    - [License](#license)  

***

## Install
**All installation instructions are for macOS**
### Setup/Installation Requirements
1. Install Java JRE (Runtime Environment).
2. Install Postgres.
3. Install Gradle.
4. Clone this repository onto your desktop. This will place the all files and folders in onto your computer.
5. Start Postgres in an additional terminal tab by entering `postgres`.
6. In yet another terminal window start psql with `psql`.
7. In psql tab enter `CREATE DATABASE wildlife_tracker;`.
8. In terminal tab enter `psql wildlife_tracker < wildlife_tracker.sql`.
9. In psql tab enter `\c wildlife_tracker` then `\dt`.
10. In terminal tab enter `gradle run`.
11. Navigate to 0.0.0.0:4567 in your internet browser.

### Manual Database Creation
To create the necessary databases, launch postgres, then psql, and run the following commands:

* `CREATE DATABASE wildlife_tracker;`
* `\c wildlife_tracker;`
* `CREATE TABLE animals (id serial PRIMARY KEY, name varchar);`
* `CREATE TABLE endangered_animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar);`
* `CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar, date TIMESTAMP);`
* `CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;`

## Usage
Visit `https://wildlife-tracker-live.herokuapp.com`

### Screen Shots
<img src="/src/main/resources/public/images/home.png" alt="Home" width="400"> <img src="/src/main/resources/public/images/addAnimal.png" alt="Add Animal" width="400"> <img src="/src/main/resources/public/images/emptyAnimals.png" alt="All Animals prior to adding animals" width="400"> <img src="/src/main/resources/public/images/animals.png" alt="All Animals post adding animals" width="400"> <img src="/src/main/resources/public/images/postSightings.png" alt="Post Animal Sighting" width="400"> <img src="/src/main/resources/public/images/details.png" alt="Animal Details" width="400">

## Known Bugs
* :bug: = S1
* :ant: = S2
* :beetle: = S3

> :ant: Can submit empty or invalid characters in a non-endangered animal sighting report.
>
> :ant: Can submit empty or invalid characters in an endangered animal sighting report.
>
> :ant: User can submit empty or invalid characters when adding an animal to the system.
>
> :ant: User can not delete entry.
>
> :bug: User can not update entry.
>
> :bug: Recording date of sighting not displayed in details.

## Technology Used
* Java
* JUnit
* Gradle
* Spark
* Velocity
* Postgres
* PSQL
* Heroku

## Specifications
|Behaviors|Input|Output|
|-----------|:-------:|:--------:|
|Application creates instance|-|true|
|Application adds unique id to instance|-|1|
|Application adds name to instance|Wolf|Wolf|
|Application categorizes instance|Endangered Animal|true|
|Application adds health to categorizes instance|Healthy|Healthy|
|Application adds age to categorizes instance|Young|Young|
|Application adds date to instance creation|April 7 2017|April 7 2017|
|Application counts the number of instances|2|2|
|Application updates instance details|Grey Wolf|Gray Wolf|
|Application captures all instances|Gray Wolf, Fox, Beaver|Gray Wolf, Fox, Beaver|

## Sources
>[Trail image](https://unsplash.com/collections/991129/trails?photo=dGDmRqzPID0)
>
>[Owl image](https://unsplash.com/collections/1039/wildlife?photo=hbqTtTX5TRc)
>
>[Fox image](https://unsplash.com/collections/1039/wildlife?photo=9rloii_qmmw)
>
>[Duck image](https://unsplash.com/collections/444531/woodland-animals?photo=yQZgEh4u-Dw)

## Maintainer
[Grace Stuart](href="https://github.com/gstuart")


## Contribute
* Questions may be submitted to gstuart.github@gmail.com.
* Pull requests accepted.
* NOTE: If editing the Readme, please conform to the [standard-readme specification](https://github.com/RichardLitt/standard-readme/blob/master/spec.md).


## License
**_[GNU GPL 3.0 License](/LICENSE.md)_** :copyright: **2017 Grace Stuart**


***

**[:top:](#wildlife_tracker)**
