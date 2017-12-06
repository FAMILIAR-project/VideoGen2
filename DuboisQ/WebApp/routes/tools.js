var express = require('express');
var exec = require('child_process').exec, child;
var router = express.Router();

router.get('/generate', function(req, res, next) {
	console.log('Generate call from client');
	child = exec('/usr/bin/java -jar ~/Projet/Cours/Projet_IDM/DuboisQ/WebApp/IDM.jar',
	  function (error, stdout, stderr){
	    console.log('stdout: ' + stdout);
	    console.log('stderr: ' + stderr);
	    if(error !== null){
	      console.log('exec error: ' + error);
	    }
	    res.send(stdout);
	});

});


module.exports = router;
