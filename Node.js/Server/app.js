const app = require('./server');
// Route to save response

const port = process.env.PORT || 5001;
app.listen(port, () => {
  console.log('Server started @ http://localhost:' + port);
});
