$(function(){
	$("#regist").click(function(){
		registFormCheck();
	});


	function registFormCheck(){
		if($("#usLoginname").val() == ""){
			$(".usLoginname").addClass("has-error");
			$("#usLoginname_helpBlock").html("请输入登录名");
			return;
		}else{
			$.ajax({
				url:'CheckUserExist',
				type:'post',
				dataType: 'json',
				data: {  
					"name":$("#usLoginname").val(),
				},
				success:function(data){
					if(data.code== 200){
						if(data.data == 1){
							$("#usLoginname_helpBlock").html("登录名已存在，请重新输入");
							return;
						}else{
							$(".usLoginname").removeClass("has-error");
							$("#usLoginname_helpBlock").html("");

							if($("#usPwd").val() == ""){
								$(".usPwd").addClass("has-error");
								return;
							}else{
								$(".usPwd").removeClass("has-error");
							}
							if($("#reUsPwd").val() == ""){
								$(".reUsPwd").addClass("has-error");
								return;
							}else if($("#usPwd").val() != $("#reUsPwd").val()){
								$(".reUsPwd").addClass("has-error");
								$("#usPwd_helpBlock").html("密码输入不一致，请重新输入");
								return;
							}else{
								$(".reUsPwd").removeClass("has-error");
								$("#usPwd_helpBlock").html("");
							}

							if($("#usName").val() == ""){
								$(".usName").addClass("has-error");
								return;
							}else{
								$(".usName").removeClass("has-error");
							}

							if($("#usPhone").val() == ""){
								$(".usPhone").addClass("has-error");
								return;
							}else{
								if(!(/^1(3|4|5|7|8)\d{9}$/.test($("#usPhone").val()))){ 
									$("#usPhone_helpBlock").html("手机号输入有误，请重新输入");
									return; 
								} else{
									$(".usPhone").removeClass("has-error");
									$("#usPhone_helpBlock").html("");
									if($("#usMail").val() == ""){
										$(".usMail").addClass("has-error");
										return false;
									}else{
										if(!(/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test($("#usMail").val()))){
											$(".usMail").addClass("has-error");
											$("#usMail_helpBlock").html("邮箱输入有误，请重新输入");
											return;
										}else{
											$(".usMail").removeClass("has-error");
											$("#usMail_helpBlock").html("");

											$.ajax({
												url:'UserRegist',
												type:'post',
												dataType: 'json',
												data: $("#regist-form").serialize(),
												success:function(data){
													if(data.code == 200){
														$("#result").html("注册成功，<a href='user-login' style='text-decoration:underline;'>去登录</a>");
													}else{
														$("#result").html("注册失败，请烧好再重试。");
													}
												},
												error:function(e){
													console.log();
												}
											});
										}
									}
								}
							}


						}
					}
				},
				error:function(e){
					alert(e.message);
				}
			});

		}
	}

	$("#login").click(function(){
		loginFormCheck();
	});
	
	function loginFormCheck(){
		if($("#usLoginname").val() == ""){
			$(".usLoginname").addClass("has-error");
			$("#usLoginname_helpBlock").html("请输入登录名");
			return;
		}else{
			$(".usLoginname").removeClass("has-error");
			$("#usLoginname_helpBlock").html("");
			
			if($("#usPwd").val() == ""){
				$(".usPwd").addClass("has-error");
				return;
			}else{
				$(".usPwd").removeClass("has-error");
				
				$.ajax({
					url:'UserLogin',
					type:'post',
					dataType: 'json',
					data:$("#login-form").serialize(),
					success:function(data){
						if(data.code== 200){
							$("#result").html("登录成功");
						}else{
							$("#result").html(data.message);
						}
					},
					error:function(e){
						alert(e.message);
					}
				});
			}
		}
	}

	$("#group-login").click(function(){
		groupLoginFormCheck();
	});
	
	function groupLoginFormCheck(){
		if($("#pgLeaderPhone").val() == ""){
			$(".pgLeaderPhone").addClass("has-error");
			$("#pgLeaderPhone_helpBlock").html("请输入登录名");
			return;
		}else{
			$(".pgLeaderPhone").removeClass("has-error");
			$("#pgLeaderPhone_helpBlock").html("");
			
			if($("#pgLeaderPwd").val() == ""){
				$(".pgLeaderPwd").addClass("has-error");
				return;
			}else{
				$(".pgLeaderPwd").removeClass("has-error");
				
				$.ajax({
					url:'GroupLogin',
					type:'post',
					dataType: 'json',
					data:$("#group-login-form").serialize(),
					success:function(data){
						if(data.code== 200){
							$("#result").html("登录成功");
						}else{
							$("#result").html(data.message);
						}
					},
					error:function(e){
						alert(e.message);
					}
				});
			}
		}
	}
	
	$("#group-regist-form").Validform({
			tiptype:3,
			label:".label",
			showAllError:true,
			datatype:{
				"zh1-6":/^[\u4E00-\u9FA5\uf900-\ufa2d]{1,6}$/
			},
			ajaxPost:true
	});	
});