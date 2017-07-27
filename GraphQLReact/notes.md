- GraphQL -> help to solved problem when the RESTful routing is sophisticated.
- Example: a query friend's company's name of a user
query {
  user(id: "23") {
    users(){
      company{
        name
      }
    }
  }
}

# Learn to be: Web developer / Mobile developer or Fullstack -> https://rallycoding.com/

# React
## Appolo-React docs
- Cach update: http://dev.apollodata.com/react/cache-updates.html
## Fullstack problem
- Need to show multiple pages: Use React Router for navigation
- Need to store user data: Store in mongodb ...
- Users shouldn't be able to see all details about other user:
- Need to validate input:
- Need some solution for authentication: Passport JS
- Passport isn't designed with GraphQL in mind:


# DB tips
- Use mlab.com for free mongodb

# Some GraphQL clients
## Lokka Client
- Verry basic queries, mutations
- Some simple caching
## Apollo Client
- The creator is also the man who created Meteor JS
- Good
## Relay
- Good performance for mobile

# Nodejs tips
## Create new npm project
npm init
## Install new packages
npm install --save packageName1 packageName2
## Nodemon
Run nodejs app every time we change server code -> Nodemon

# Json server
- small fake json server
https://github.com/typicode/json-server

# Other ref
- Online diagram tools: draw.io, balsamiq.com
- Key for balsamiq 3.0 (Name: Flash // Serial: eNrzzU/OLi0odswsqnHLSSzOqDGoca7JKCkpsNLXLy8v1ytJTczVLUotKNFLzs8FAJHYETc=)
