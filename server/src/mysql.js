import { Sequelize } from 'sequelize';
import mysql2 from 'mysql2';
import DebugLib from 'debug';

export const mySqlPort = '4444';

const debug = new DebugLib('server:mysql');

export const sequelize = new Sequelize('eoloplantsDB', 'root', 'password', {
    dialect: 'mysql',
    port: mySqlPort,
    dialectModule: mysql2,
    logging: false
});

process.on('exit', async () => {
    await sequelize.close();
    debug(`Closing mysql connection`);
});
