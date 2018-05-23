const bodyParser = require('body-parser');
const db = require('./db');
const express = require('express');
const ehb = require('express-handlebars');
const session = require('express-session');
const bcrypt = require('bcrypt');

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
      console.log(err);
      console.log(result);
      if(result.length != 0) {
        console.log(result[0].password);
        console.log(result[0].username);
        console.log(result);
        if(bcrypt.compareSync(password, result[0].password)) {
          req.session.user = result[0].username;
          res.redirect('/User_Management');
        } else {
          let error = "invalid password";
          res.render('index', { layout: 'login-temp' , error });
        }
      } else {
        let error = "invalid username";
        res.render('index', { layout: 'login-temp' , error });
      }
    })
})

app.get('/User_Management', (req, res) => {
  if(req.session.user) {
    db.query("SELECT * FROM company WHERE block = 0 AND status = 1",
    (err, bCompanies) => {
      db.query("SELECT * FROM client WHERE block = 0 AND status = 1",
      (err, bClients) => {
        db.query("SELECT * FROM company WHERE block = 1 AND status = 1",
        (err, uCompanies) => {
          db.query("SELECT * FROM client WHERE block = 1 AND status = 1",
          (err, uClients) => {
            if(bCompanies.length != 0 || uCompanies.length != 0 || bClients.length != 0 || uClients.length != 0) {
              res.render('User_Management', { bCompanies, bClients, uCompanies, uClients });
            } else {
              let text = "No Users can be manage right now";
              res.render('empty', { text });
            }
          })
        })
      })
    })
  } else {
    res.redirect('/');
  }
});

app.post('/Manage_User', (req, res) => {
  let manage = req.body.btn;
  console.log(manage);
  for(key in manage) {
    let code = key.charAt(0);
    let id = key.replace(key.charAt(0), "");
    switch(code) {
      case 'a':
        db.query("UPDATE company SET block = 1 WHERE comp_id = ?",
          [ parseInt(id, 10) ],
          (err, results) => {
            console.log(err);
          })
        break;
      case 'b':
        db.query("UPDATE client SET block = 1 WHERE client_id = ?",
          [ parseInt(id, 10) ],
          (err, results) => {
            console.log(err);
          })
        break;
      case 'c':
        db.query("UPDATE company SET block = 0 WHERE comp_id = ?",
          [ parseInt(id, 10) ],
          (err, results) => {
            console.log(err);
          })
        break;
        case 'd':
          db.query("UPDATE client SET block = 0 WHERE client_id = ?",
            [ parseInt(id, 10) ],
            (err, results) => {
              console.log(err);
            })
          break;
    }
  }
  res.redirect('/User_Management');
});

app.get('/transaction', (req, res) => {
  if(req.session.user) {
    db.query("SELECT name, COUNT(trans_id) as count from company join transaction on company.comp_id = transaction.comp_id WHERE status = 1 GROUP by name",
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

app.get('/signout', (req, res) => {
  req.session.destroy();
  res.redirect('/');
});

app.get('/registration', (req, res) => {
  let redirect = req.query.redirect;
  res.render('registration', { redirect, layout: 'login-temp' });
});

app.post('/register', (req, res) => {
  console.log('test');
  const saltRounds = 10;
  let name = req.body.name;
  let username = req.body.uname;
  let uType = req.body.utype;
  let email = req.body.email;
  let address = req.body.address;
  let cnum = req.body.contact;
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
        };
        db.query("SELECT * from company where email = ?",
          [ email ],
          (err, results) => {
            if(results.length != 0) {
              errors.push({
                "error": "email address is already in use!"
              });
            };
            if(errors.length != 0) {
              let msg = ("Error!\n");
              errors.forEach(error => {
                msg += (error.error + "\n");
              });
              res.status(400).json(errors);
            } else {
              let hashed = bcrypt.hashSync(password, saltRounds);
              db.query("INSERT INTO company (name, address, username, contact, email, password) VALUES (?, ?, ?, ?, ?, ?)",
                [ name, address, username, cnum, email, hashed],
                (err, results) => {
                  console.log(err);
                });
                res.send(JSON.stringify(redirect));
            }
        });
    });
  } else {
    db.query("SELECT * from client where username = ?",
      [ username ],
      (err, results) => {
        console.log(results);
        if(results.length != 0) {
          console.log("not empty");
          errors.push({
            "error": "username already in use!"
          });
        };
        db.query("SELECT * from client where email = ?",
          [ email ],
          (err, results) => {
            if(results.length != 0) {
              errors.push({
                "error": "email address is already in use!"
              });
            };
            console.log(errors);
            if(errors.length != 0) {
              let msg = ("Error!\n");
              errors.forEach(error => {
                msg += (error.error + "\n");
              });
              res.status(400).json(errors);
            } else {
              const salt =  bcrypt.genSaltSync(saltRounds, 'a');
              let hashed = bcrypt.hashSync(password, salt);
              db.query("INSERT INTO client (name, address, username, contact, email, password) VALUES (?, ?, ?, ?, ?, ?)",
                [ name, address, username, cnum, email, hashed],
                (err, results) => {
                  console.log(err);
                });
                res.send(JSON.stringify(redirect));
            };
        });
    });
  }
});

app.get('/Registration_Management', (req, res) => {
  if(req.session.user) {
    db.query(`SELECT * from company where status = 0`,
      (err, companies) => {
      db.query("SELECT * from client where status = 0",
      (err, clients) => {
        if (companies.length != 0 || clients.length != 0) {
          res.render('Registration_Management', { companies, clients });
        } else {
          let text = "No one to Accept or Reject";
          res.render('empty', { text });
        };
      })
    })
  } else {
    res.redirect('/');
  }
});

app.post('/Manage_Registration', (req, res) => {
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
  res.redirect('/Registration_Management');
})

const port = process.env.PORT || 5001;
const ip = '192.168.1.102';
app.listen(port, ip, () => {
  console.log('Server started @ http://webtechadmin.org:' + port);
});
