angular.module('myApp', []).controller('studentCtrl', function($scope,$http) {
	$scope.stuList=[];
	var rootPathWeb="";
	//---1.查询用户列表(首页加载)---	
	window.onload=function($event,options){
		//get请求带参写法:$http.get("/xx/xx?param="+value).then(function(response) {....}
		$http.get("/angularUser/list").then(
			function(response) {
			   $scope.list=response.data;
		    }
		 )
	} 
	//--新增页面
    $scope.addUser=function(){  
   	  $("#add-user").modal('show');
   	}  
   //---2.新增用户信息----
    $scope.saveUser= function(){  
		var username=$scope.username;
		var nickname=$scope.nickname;
		var dataParam = {username:username,nickname:nickname};
        $http({  
    		method:'POST',  
    		url:'/angularUser/insert',  
    		params:dataParam  
        }).then(
    	   function(response){  
    		   $("#add-user").modal('hide');
        	   window.onload();  
    	  }
        );   
     }
    //--3.删除用户信息-----  
    $scope.deleteUser= function($event,options){  
    	var userId=options.data.userId;
        var dataParam = {userId:userId};  
        $http({  
    		method:'POST',  
    		url:'/angularUser/delete',  
    		params:dataParam  
        }).then(
    	   function(response){      		  
        	   window.onload();  
    	  }
        );
     } 
    //---4.跳转到更新用户信息页面----   
    $scope.toUpdateUser= function($event,options){     	
    	var userId=options.data.userId;
        var dataParam = {userId:userId}; 
        $http({  
    		method:'POST',  
    		url:'/angularUser/findById',  
    		params:dataParam  
        }).then(
    	   function(response){      		  
    		   $scope.user=response.data;       	  
        	   $("#update-user").modal('show');
    	  }
        );  
     }  
    //<!---5.更新用户信息----->
    $scope.updateUser= function(){  
    	var userId=$scope.user.userId;
		var username=$scope.user.username;
		var nickname=$scope.user.nickname;
        var dataParam = {userId:userId,username:username,nickname:nickname}; 
        $http({  
    		method:'POST',  
    		url:'/angularUser/update',  
    		params:dataParam  
        }).then(
    	   function(response){      		  
    		  $("#update-user").modal('hide'); 
          	  window.onload();
    	  }
        );   
     }  
    //<!---6.查看用户信息----->
    $scope.getUserDetail=function($event,options){  
    	$("#detail_student").modal('show');
    	var userId=options.data.userId;
        var dataParam = {userId:userId};    
        $http({  
    		method:'POST',  
    		url:'/angularUser/findById',  
    		params:dataParam  
        }).then(
    	   function(response){      		  
    		   $scope.user=response.data;
        	   $("#detail-user").modal('show');
    	  }
        );   
    }  
    
});