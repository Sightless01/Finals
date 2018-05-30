const mysql = require('mysql');
const connection = mysql.createConnection({
  user: 'root',
  password: '',
  host: '192.168.1.117',
  port: 3306,
  database: 'database',
  connectionLimit: 400
});

module.exports = connection;
