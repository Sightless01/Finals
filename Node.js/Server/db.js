const Sequelize = require('sequelize');

const sequelize = new Sequelize('../../database', null, null, {
  dialect: 'sqlite',
  operatorsAliases: false,
  storage: '../../database.sqlite'
});

module.exports = sequelize;
