import { server } from './express.js';
import { sequelize } from './mysql.js';

await sequelize.sync();
console.log('Connected to MySQL');

server.listen(5555, () => console.log(`Server listening on port 5555!`));
