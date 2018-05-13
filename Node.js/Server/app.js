const Sequelize = require('sequelize');
const bodyParser = require('body-parser');
const db = require('./db');
const express = require('express');
const ehb = require('express-handlebars');
const session = require('express-session');
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

app.get('/test', (req, res) => {
  db.query('SELECT * from company', { type: Sequelize.QueryTypes.SELECT })
    .then(companies => {
      console.log(companies);
      res.render('test', { companies });
    })
});

app.post('/register', (req, res) => {
  console.log(req.body.CoCname);
  res.render('registration');
})

const port = process.env.PORT || 5001;
app.listen(port, () => {
  console.log('Server started @ http://localhost:' + port);
});
