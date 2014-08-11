'use strict';

/**
 * @ngdoc function
 * @name glassApp.controller:DatasetCtrl
 * @description
 * # DatasetCtrl
 * Controller of the glassApp
 */
//inject angular file upload directives and service.
angular.module('glassApp').controller('DatasetCtrl', function ($scope, $upload, dataservice) {
	
	$scope.showPersonalDataGrid = true;
	$scope.showPublicDataGrid = true;

	$scope.personalGridData = "[]";
    $scope.personalGridOptions = { data: 'personalGridData' };
    $scope.publicGridData = "[]";
    $scope.publicGridOptions = { data: 'publicGridData' };
	// setup data
	dataservice.getUserDatasets().then(function(data) {
		console.log(data);
	});
	
	$scope.onFileSelect = function($files) {
	    //$files: an array of files selected, each file has name, size, and type.
	    for (var i = 0; i < $files.length; i++) {
	      var file = $files[i];
	      $scope.upload = $upload.upload({
	        url: 'fileupload',
	        method: 'POST',
	        data: {myObj: $scope.myModelObj},
	        file: file,
	        //formDataAppender: function(formData, key, val){}
	      }).progress(function(evt) {
	        console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
	      }).success(function(data, status, headers, config) {
	        // file is uploaded successfully
	        console.log(data);
	        
	      });
	    }
	  };
});
