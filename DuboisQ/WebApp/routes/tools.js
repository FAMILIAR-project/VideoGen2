var express = require('express');
var exec = require('child_process').exec, child;
var router = express.Router();

var interval = setInterval(function() {
	child = exec('find public/videogen/*.mp4 -maxdepth 1 -mmin +1 -delete',
		function (error, stdout, stderr){
			if(error !== null){
				console.log('No video to remove..');
			}
			else{
				console.log("Old video removed ..");
			}
	});
}, 30000);


router.get('/generate', function(req, res, next) {
	console.log('Generate call from client');

	child = exec('/usr/bin/java -jar IDM.jar',
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
