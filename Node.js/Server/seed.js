const db = require('./db');
const Sequelize = require('sequelize');

// Create users table sample
const createUsersSql = `
CREATE TABLE IF NOT EXISTS users (
  id INTEGER PRIMARY KEY,
  name text NOT NULL,
  username text NOT NULL
)
`;
// Insert Sample
async function insertQuestions () {
  const q1 = `INSERT INTO questions (quiz_code, content) VALUES ('1', 'Sino si rica');`
  const q1Options = id => `
    INSERT INTO options(question_id, code, option, is_correct)
    VALUES
      (${id}, 'A', 'yung naka pink', 0),
      (${id}, 'B', 'yung naka black', 1),
      (${id}, 'C', 'yung naka red', 0)
    ;
  `;

  const q2 = `INSERT INTO questions (quiz_code, content) VALUES ('1', 'This is question 2');`
  const q2Options = id => `
    INSERT INTO options(question_id, code, option, is_correct)
    VALUES
      (${id}, 'A', 'This is A', 0),
      (${id}, 'B', 'This is B', 1),
      (${id}, 'C', 'This is C', 0)
    ;
  `;
  const [q1Id] = await db.query(q1, { type: Sequelize.QueryTypes.INSERT })
  const [q2Id] = await db.query(q2, { type: Sequelize.QueryTypes.INSERT })

  return Promise.all([
    db.query(q1Options(q1Id)),
    db.query(q1Options(q2Id))
  ])
}

const print = e => console.log(e)


db.authenticate()
  .then(() => console.log('Connected'))
  .then(() => Promise.all([
    db.query(createUsersSql),
    db.query(createQuestionSql),
    db.query(createOptionsSql),
    db.query(createResponsesSql)
  ]))
  .then(() => insertQuestions())
  .then(() => db.query('SELECT * FROM questions', { type: Sequelize.QueryTypes.SELECT }))
  .then(print)
  .then(() => db.query('SELECT * FROM options', { type: Sequelize.QueryTypes.SELECT }))
  .then(print)
;
