(function() {
    'use strict';

    angular
        .module('videoGenWebApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state','$http','$resource' ];

    function HomeController ($scope, Principal, LoginService, $state, $http,$resource) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        };
        
        	
        $scope.random = function () {
        	        		
            	var entry = $resource('/api/random').get({}, function() {
            	    console.log(entry.entity);
            	    $scope.randomDone = true;
            	    $scope.gifExported = false;
            	    $scope.gifLink = "";
            	  }); // get() returns a single entry
            	};
            	
        $scope.export = function () {
        	var entry = $resource('/api/gif').get({}, function() {
        	    console.log(entry.entity);
                $scope.showGif = true;
                $scope.gifLink = "content/folder/gifs/"+$scope.videogenName+".gif";
        	    $scope.gifExported = true;
        	  }); // get() returns a single entry
        	};
            	
        $scope.videogenName = "variante";
        var modelPath = $scope.videogenName+".videogen";
        $scope.randomDone = false;
        $scope.showGif = false;
        $scope.gifLink = "";
	    $scope.gifExported = false;
	    
	    $scope.videoPath = "content/folder/generatedVideos/"+$scope.videogenName+".mp4";


	    
	    /*var image = document.getElementById("gif");
	    var downloadingImage = new Image();
		downloadingImage.onload = function(){
 			image.src = this.src;   
		};
		downloadingImage.src = $scope.gifLink;*/
			
    }
})();
