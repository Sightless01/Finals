/**
 * http server in place of express
 */

const handlebars = require('handlebars');
const Sequelize = require('sequelize');
const http = require('http');
const path = require('path');
const fs = require('fs');
const qs = require('querystring');
const bodyParser = require('body-parser');
const { promisify } = require('util');
const { parse: urlParse }  = require('url');
const db = require('../db');

const readFile = promisify(fs.readFile);

const renderView = async (name, data) => {
  const layoutFile = await readFile(path.join(process.cwd(), 'views/layouts/default.handlebars'), 'utf8');
  const render = handlebars.compile(layoutFile);

  const viewFile = await readFile(path.join(process.cwd(), `views/${name}.handlebars`), 'utf8');
  const renderTemplate = handlebars.compile(viewFile);

  return render({ body: renderTemplate(data) });
}

const dataResolver = async (name) => {
  if (name === 'admins') {
    return {
      admin: await db.query('SELECT * FROM admin', { type: Sequelize.QueryTypes.SELECT })
    };
  } else {
    return {};
  }

};

server = http.createServer(async (req, res) => {
  const headers = {
    'Content-Type': 'text/html'
  };

  try {
    const url = urlParse(req.url);
    const splitPath = url.path.split('/');
    const viewName = url.pathname != "/" ? splitPath[1] : 'index';

    // ex: /hello => hello, /hello/123 => /hello
    if(req.method === "GET") {

      const view = await renderView(viewName, await dataResolver(viewName));

      // Send response
      res.writeHead(200, headers);

      res.write(view);
    } else if(req.method === "POST") {
      var reqBody = '';
      req.on('data', data => {
        reqBody+=data;
      });
      if(viewName === 'register') {
        req.on('end', () => {
          let formData = qs.parse(reqBody);
          var username = formData.username;
          var password = formData.password;

          const insert = `INSERT INTO admin (username, password) VALUES ('${username}', '${password}');`;
          db.query(insert, { type: Sequelize.QueryTypes.INSERT });
        })
      }
    }


  } catch (err) {
    res.writeHead(500, headers);
    res.write(err.stack)
    res.write('\n');
  }

  return res.end();
});

module.exports = server;
