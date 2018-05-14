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
app.use(bodyParser.json());

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
  const redirect = req.query.redirect;

  res.render('registration', { redirect });
});

app.get('/test', (req, res) => {
  db.query('SELECT * from company where status == 0', { type: Sequelize.QueryTypes.SELECT })
    .then(companies => {
      db.query('SELECT * from client where status == 0', { type: Sequelize.QueryTypes.SELECT })
        .then(clients => {
          res.render('user_management', { companies, clients });
        })
    })
});

app.get('/block', (req, res) => {
  db.query('SELECT * from company where block == 0', { type: Sequelize.QueryTypes.SELECT })
    .then(companies => {
      db.query('SELECT * from client where block == 0', { type: Sequelize.QueryTypes.SELECT })
        .then(clients => {
          res.render('blocking', { companies, clients });
        })
    })
});

app.post('/register', (req, res) => {
  let name = req.body.fname;
  let username = req.body.uname;
  let uType = req.body.utype;
  let email = req.body.eaddress;
  let address = req.body.paddress;
  let cnum = req.body.contactnum;
  let pass = req.body.password;

  let errors = [];
  db.query('SELECT * from ? where username == ?', {
    replacements: [ uType, username ],
    type: Sequelize.QueryTypes.SELECT
  })
    .then(results => {
      console.log(results);
    })
  errors.push({
    error: "username already in use"
  });

  if(errors) {

  } else {
    res.redirect(req.body.redirect);
  }
  //res.render('registration');
});

app.post('/test', (req, res) => {
  console.log(req.body);
})

const port = process.env.PORT || 5001;
app.listen(port, () => {
  console.log('Server started @ http://localhost:' + port);
});
