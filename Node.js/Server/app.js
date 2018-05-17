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
  let redirect = req.query.redirect;

  res.render('registration', { redirect });
});

app.get('/manage_user', (req, res) => {
  db.query('SELECT * from ? where status == 0', {
    replacements: [ 'company' ],
    type: Sequelize.QueryTypes.SELECT
  })
    .then(companies => {
      db.query('SELECT * from client where status == 0', { type: Sequelize.QueryTypes.SELECT })
        .then(clients => {
          console.log(companies);
          console.log(clients);
          res.render('manageUser', { companies, clients });
        })
    })
});

app.post('/block', (req, res) => {
  console.log(req.body);
  console.log();
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

app.get('/unblock', (req, res) => {
  db.query('SELECT * from company where block == 1', { type: Sequelize.QueryTypes.SELECT })
    .then(companies => {
      db.query('SELECT * from client where block == 1', { type: Sequelize.QueryTypes.SELECT })
        .then(clients => {
          res.render('unblock', { companies, clients });
        })
    })
});

app.get('/transaction', (req, res) => {
  db.query('SELECT name, COUNT(trans_id) as count from company join transact on company.comp_id = transact.comp_id GROUP by name', {
    type: Sequelize.QueryTypes.SELECT
  })
  .then((result) => {
    let transactArr = [];
    for(let i = 0; i < result.length; i++) {
      transactArr.push({
        "name": result[i].name,
        "count": result[i].count,
        "total": parseInt(result[i].count) * 10
      });
    }
    console.log(transactArr);
    res.render('transaction', { transactArr });
  })
});

app.post('/register', (req, res) => {
  let name = req.body.fname;
  let username = req.body.uname;
  let uType = req.body.utype;
  let email = req.body.eaddress;
  let address = req.body.paddress;
  let cnum = req.body.contactnum;
  let password = req.body.password;
  let redirect = {
    "redirect" : req.body.redirect
  };

  console.log(req.body.redirect);
  let errors = [];
  let form = {
    "name": name,
    "uname": username,
    "email": email,
    "address": address,
    "cnum": cnum
  }
  console.log(uType, username);
  db.query('SELECT * from ? where username == ?', {
    replacements: [ uType, username ],
    type: Sequelize.QueryTypes.SELECT
  })
    .then(results => {
      console.log(results);
      if(results.length != 0) {
        errors.push({
          "error": "username already in use!"
        });
      }
    })
    .then(() => {
      db.query('SELECT * from ? where email == ?', {
        replacements: [ uType, email ],
        type: Sequelize.QueryTypes.SELECT
      })
        .then(results => {
          if(results.length != 0) {
            errors.push({
              "error": "email address is already in use!"
            });
          }
        })
        .then(() => {
          if(errors.length != 0) {
            let msg = ("Error!\n");
            errors.forEach(error => {
              msg += (error.error + "\n");
            })
            console.log("wut");
            console.log(form);
            console.log(msg);
            res.writeHead(400, { 'Content-Type': 'application/json' });
            res.end(JSON.stringify(errors));
          } else {
            let addressType = uType == 'company' ? 'comp_address' : 'address';
            let pass = uType == 'company' ? 'pw': 'password';

            db.query("INSERT INTO ? (name, ?, username, contact, email, ?) VALUES (?, ?, ?, ?, ?, ?)", {
              replacements: [ uType, addressType, pass, name, address, username, cnum, email, password],
              type: Sequelize.QueryTypes.INSERT
            })
            res.send(JSON.stringify(redirect));
          }
        });
    })
});

app.post('/test', (req, res) => {
  console.log(req.body);
  let manage = req.body.btn;
  for(key in manage) {
    let response = manage[key];
    let uType = key.charAt(0) == 'p' ? 'company' : 'client';
    let idType = uType == 'company' ? 'comp_id' : 'client_id';
    let id = key.replace(key.charAt(0), "");
    console.log(uType, id, response);
    if(manage[key] == 'accept') {
      db.query('UPDATE ? SET status = 1 WHERE ? = ?', {
        replacements: [ uType, idType, parseInt(id, 10) ],
        type: Sequelize.QueryTypes.UPDATE,
        raw: true
      })
      .then(results => {
        console.log(results);
      })
    } else {
      db.query('DELETE from ? where ? = ?', {
        replacements: [ uType, idType, parseInt(id, 10) ],
        type: Sequelize.QueryTypes.DELETE,
        raw: true
      })
      .then(results => {
        console.log(results);
      })
    }
  };
})

const port = process.env.PORT || 5001;
app.listen(port, () => {
  console.log('Server started @ http://localhost:' + port);
});
