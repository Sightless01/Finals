const express = require('express');
const fs = require('fs');
const bodyParser = require('body-parser');
const db = require('./db');

const app = express();

app.use('/static', express.static('public'));

const port = process.env.PORT || 5002;
const ip = '192.168.1.102';

app.listen(port, () => {
  console.log('Server started @http://database.org:' + port);
})
