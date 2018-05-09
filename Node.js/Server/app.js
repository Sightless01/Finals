const Sequelize = require('sequelize');
const bodyParser = require('body-parser');
const db = require('./db');
const express = require('express');
const ehb = require('express-handlebars');
const $ = require('jquery');

const app = express();

app.engine('handlebars', ehb({ defaultLayout: 'default' }));
app.set('view engine', 'handlebars');

app.use('/static', express.static('public'));

app.use(bodyParser.urlencoded({ extended: true }));

app.get('/', (req, res) => {
  res.render('index');
});

app.get('/admins', (req, res) => {
  db.query('SELECT * FROM admin', { type: Sequelize.QueryTypes.SELECT })
    .then(admins => {
      res.render('admins', { admins });
    });
});

app.get('/registration', (req, res) => {
  res.render('registration');
});

app.post('/', (req, res) => {
  var username = req.query.username;
  var password = req.query.password;

  const insert = `INSERT INTO admin (username, password) VALUES ('${username}', '${password}');`;
  db.query(insert, { type: Sequelize.QueryTypes.INSERT });
});

const port = process.env.PORT || 5001;
app.listen(port, '192.168.1.40', () => {
  console.log('Server started @ http://localhost:' + port);
});
