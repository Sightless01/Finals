/**
 * http server in place of express
 */

const handlebars = require('handlebars');
const Sequelize = require('sequelize');
const http = require('http');
const path = require('path');
const fs = require('fs');
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
  }

  // Return empty object by default
  return {};
};

server = http.createServer(async (req, res) => {
  const headers = {
    'Content-Type': 'text/html'
  };

  try {
    const url = urlParse(req.url);

    // ex: /hello => hello, /hello/123 => /hello
    const splitPath = url.path.split('/');
    const viewName = url.pathname != "/" ? splitPath[1] : 'index';

    const view = await renderView(viewName, await dataResolver(viewName));

    // Send response
    res.writeHead(200, headers);

    res.write(view);

  } catch (err) {
    res.writeHead(500, headers);
    res.write(err.stack)
    res.write('\n');
  }

  return res.end();
});

module.exports = server;
