const mysql = require('mysql');

const connection = mysql.createConnection({
  user: 'root',
  password: '',
  host: 'localhost',
  port: 3306,
  database: 'webtech',
  connectionLimit: 400
});

module.exports = connection;
