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
app.use(session({ secret: 'webtechLAB', resave: false, saveUninitialized: false }));

app.get('/', (req, res) => {
  res.render('index', { layout: 'login-temp' });
});

app.post('/login', (req, res) => {
  let username = req.body.username;
  let password = req.body.password;
  db.query('SELECT username, password FROM admin WHERE username = ?',
    [ username ],
    (err, result) => {
      if(result.length != 0) {
        console.log(result[0].password);
        console.log(result[0].username);
        console.log(result);
        if(result[0].password == password) {
          req.session.user = result[0].username;
          res.redirect('manage_user');
        }
      } else {
        let error = "error";
        res.render('index', { layout: 'login-temp' , error });
      }
    })
})

app.get('/registration', (req, res) => {
  let redirect = req.query.redirect;

  res.render('registration', { redirect });
});

app.get('/manage_user', (req, res) => {
  if(req.session.user) {
    db.query(`SELECT * from company where status = 0`,
      (err, companies) => {
      db.query("SELECT * from client where status = 0",
      (err, clients) => {
        res.render('manageUser', { companies, clients });
      })
    })
  } else {
    res.redirect('/');
  }
});

app.post('/block', (req, res) => {
  let block = req.body.btn;
  for(key in block) {
    let uType = key.charAt(0) == 'p' ? 'company' : 'client';
    let id = key.replace(key.charAt(0), "");
    if(uType == 'company') {
      db.query("UPDATE company SET block = 1 WHERE comp_id = ?",
        [ parseInt(id, 10) ],
        (err, results) => {
          console.log(err);
        })
    } else {
      db.query("UPDATE client SET block = 1 WHERE client_id = ?",
        [ parseInt(id, 10) ],
        (err, results) => {
          console.log(err);
        })
    }
  }
  res.redirect('/block');
});

app.post('/unblock', (req, res) => {
  let block = req.body.btn;
  for(key in block) {
    let uType = key.charAt(0) == 'p' ? 'company' : 'client';
    let id = key.replace(key.charAt(0), "");
    if(uType == 'company') {
      db.query("UPDATE company SET block = 0 WHERE comp_id = ?",
        [ parseInt(id, 10) ],
        (err, results) => {
          console.log(err);
        })
    } else {
      db.query("UPDATE client SET block = 0 WHERE client_id = ?",
        [ parseInt(id, 10) ],
        (err, results) => {
          console.log(err);
        })
    }
  }
  res.redirect('/unblock');
})

app.get('/block', (req, res) => {
  if(req.session.user) {
    db.query("SELECT * from company where block = 0",
    (err, companies) => {
      db.query("SELECT * from client where block = 0",
      (err, clients) => {
        res.render('block', { companies, clients });
      })
    })
  } else {
    res.redirect('/');
  }
});

app.get('/unblock', (req, res) => {
  if(req.session.user) {
    db.query("SELECT * from company where block = 0",
    (err, companies) => {
      db.query("SELECT * from client where block = 0",
      (err, clients) => {
        res.render('block', { companies, clients });
      })
    })
  } else {
    res.redirect('/');
  }
});

app.get('/transaction', (req, res) => {
  if(req.session.user) {
    db.query("SELECT name, COUNT(trans_id) as count from company join transaction on company.comp_id = transaction.comp_id GROUP by name",
    (err, result) => {
      let transactArr = [];
      console.log(result);
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
  } else {
    res.redirect('/');
  }
});

app.get('/manageUser', (req, res) => {
  db.query('SELECT * from company', { type: Sequelize.QueryTypes.SELECT })
    .then(companies => {
      res.render('manageUser', { companies });
    })
});

app.get('/unblock', (req, res) => {
  db.query('SELECT * from company', { type: Sequelize.QueryTypes.SELECT })
    .then(companies => {
      res.render('unblock', { companies });
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
  let errors = [];
  if(uType == 'company') {
    db.query("SELECT * from company where username = ?",
      [ username ],
      (err, results) => {
        if(results.length != 0) {
          errors.push({
            "error": "username already in use!"
          });
        }
    });
    db.query("SELECT * from company where email = ?",
      [ email ],
      (err, results) => {
        if(results.length != 0) {
          errors.push({
            "error": "email address is already in use!"
          });
        }
    })
    if(errors.length != 0) {
      let msg = ("Error!\n");
      errors.forEach(error => {
        msg += (error.error + "\n");
      });
      res.writeHead(400, { 'Content-Type': 'application/json' });
      res.end(JSON.stringify(errors));
    } else {
      db.query("INSERT INTO company (name, address, username, contact, email, password) VALUES (?, ?, ?, ?, ?, ?)",
        [ name, address, username, cnum, email, password],
        (err, results) => {
          console.log(err);
        })
        res.send(JSON.stringify(redirect));
    }
  } else {
    db.query("SELECT * from client where username = ?",
      [ username ],
      (err, results) => {
        if(results.length != 0) {
          errors.push({
            "error": "username already in use!"
          });
        }
    });
    db.query("SELECT * from client where email = ?",
      [ email ],
      (err, results) => {
        if(results.length != 0) {
          errors.push({
            "error": "email address is already in use!"
          });
        }
    })
    if(errors.length != 0) {
      let msg = ("Error!\n");
      errors.forEach(error => {
        msg += (error.error + "\n");
      });
      res.writeHead(400, { 'Content-Type': 'application/json' });
      res.end(JSON.stringify(errors));
    } else {
      db.query("INSERT INTO client (name, address, username, contact, email, password) VALUES (?, ?, ?, ?, ?, ?)",
        [ name, address, username, cnum, email, password],
        (err, results) => {
          console.log(err);
        })
        res.send(JSON.stringify(redirect));
    }
  }
});

app.post('/manage', (req, res) => {
  let manage = req.body.btn;
  for(key in manage) {
    let response = manage[key];
    let uType = key.charAt(0) == 'p' ? `company` : `client`;
    let id = key.replace(key.charAt(0), "");
    if(uType == 'company') {
      if(manage[key] == 'accept') {
        db.query("UPDATE company SET status = 1 WHERE comp_id = ?",
        [ parseInt(id, 10) ],
        (err, results) => {
          console.log(err);
        })
      } else {
        db.query("DELETE from company where comp_id = ?",
          [ parseInt(id, 10) ],
          (err, results) => {
            console.log(err);
          })
      }
    } else {
      if(manage[key] == 'accept') {
        db.query("UPDATE client SET status = 1 WHERE client_id = ?",
        [ parseInt(id, 10) ],
        (err, results) => {
          console.log(err);
        })
      } else {
        db.query("DELETE from client where client_id = ?",
          [ parseInt(id, 10) ],
          (err, results) => {
            console.log(err);
          })
      }
    }
  };
  res.redirect('/manage_user');
})

app.post('/manageUser', (req, res) => {
  console.log(req.body);
})

const port = process.env.PORT || 5001;
app.listen(port, () => {
  console.log('Server started @ http://localhost:' + port);
});
