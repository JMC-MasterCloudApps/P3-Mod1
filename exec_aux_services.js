const { spawnSync } = require('child_process');

function exec(serviceName, command){

  console.log(`Starting docker image for [${serviceName}]`);
  console.log(`Command: ${command}`);

  spawnSync(command, [], {

    shell: true,
    stdio: 'inherit'
  });
}

exports.mongoContainer = 'p3MongoDB'
exports.mongoExpressContainer = 'p3MongoExpress'
exports.mySqlContainer = 'p3MySql'
exports.mongoPort = '2222'
exports.mongoExpressPort = '3333'
exports.mySqlPort = '4444'

const mongoContainer = 'p3MongoDB'
const mongoExpressContainer = 'p3MongoExpress'
const mySqlContainer = 'p3MySql'
const mongoPort = '2222'
const mongoExpressPort = '3333'
const mySqlPort = '4444'

exec('MongoDB', `docker run --rm -d -p ${mongoPort}:27017 --name ${mongoContainer} mongo:4.4-bionic`)
exec('MongoExpress', `docker run --rm --link ${mongoContainer}:mongo -d -p ${mongoExpressPort}:8081 --name ${mongoExpressContainer} -e ME_CONFIG_MONGODB_URL="mongodb://mongo:${mongoPort}" mongo-express`)
exec('MySQL', `docker run --rm -d -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=eoloplantsDB -p ${mySqlPort}:3306 --name ${mySqlContainer} mysql:8.0.22`);
