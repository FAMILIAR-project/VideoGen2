var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Dog videos generator',author: 'Mark HERVAGAULT & Nathan WANONO' });
});

module.exports = router;
