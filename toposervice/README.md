## start-up MONGO-DB 'p3mongodb'
> docker run --rm -p 2222:27017 -d --name p3mongodb mongo:4.4-bionic

## start-up MONGO-EXPRESS
> docker run --rm --link p3mongodb:mongo -p 3333:8081 -e ME_CONFIG_MONGODB_URL="mongodb://mongo:2222" mongo-express

localhost:3333