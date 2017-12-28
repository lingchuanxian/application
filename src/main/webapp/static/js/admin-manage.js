/**
 * 
 */

$(function(){
	var datagrid; //定义全局变量datagrid
	var editRow = undefined; //定义全局变量：当前编辑的行
	datagrid = $("#user-tb").datagrid({
		title:"用户列表",
		method:"POST",
		url:"admin/admin/GetAdminList",
		idField:'adId',
		rownumbers: true,
		singleSelect:true,
		width: $(window).width() - 6,
		height: $(window).height() - 110,
		striped: true, //行背景交换
		fitColumns:false,
		pagination:true,//分页控件 
		pageSize: 20,//每页显示的记录条数，默认为10 
		pageList: [10,20,50,100],//可以设置每页记录条数的列表 
		loadFilter: function(data){
			if (data.code == 200){
				return data.data;
			}else{
				HandleException(data);
			}
		},
		columns:[[
			{
				field:'adId',
				title:"管理员编号",
				width:100,
				align:'center',
			},{
				field:'adName',
				title:"管理员名称",
				width:220,
				align:'center',
			},{
				field:'adLoginname',
				title:"登录账号",
				width:220,
				align:'center',
			},{
				field:'adLastlogindate',
				title:"上次登陆时间",
				width:220,
				align:'center',
				formatter: function(value,row,index){
					return jsonTimeStamp(value);
				}
			},{
				field:'adLastloginip',
				title:"上次登陆IP",
				width:220,
				align:'center',
			},{
				field:'adAdddate',
				title:"添加时间",
				width:220,
				align:'center',
				formatter: function(value,row,index){
					return jsonTimeStamp(value);
				}
				
			},{
				field:'adState',
				title:"是否启用",
				width:180,
				align:'center',
				formatter: function(value,row,index){
					if(value == 0){
						return '<a class="state-yes" href="javascript:void(0)">是</a>';
					}else{
						return '<a class="state-no" href="javascript:void(0)">否</a>';
					}
				}
			}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-user-add',
				handler:function(){
					$("#user-form").form("disableValidation");
					$('#user-box').dialog("open");
				}
			},'-',{
				text:'重置密码',
				iconCls:'icon-key-go',
				handler:function(){
					resetPassword();
				}
			},'-',{
				text:'删除',
				iconCls:'icon-user-delete',
				handler:function(){
					doDelete(datagrid);
				}
			},'-',{
				id:'start',
				text:'启用',
				iconCls:'icon-user-accept16',
				handler:function(){
					changeState(0);
				}
			},{
				id:'stop',
				text:'停用',
				iconCls:'icon-user-reject16',
				handler:function(){
					changeState(1);
				}
			}],
			onBeforeLoad:function(){
				$("#start").hide();
				$("#stop").hide();
			},
			onLoadSuccess: function(row){
				$(".state-yes").linkbutton({ text: '是', plain: true, iconCls: 'icon-ok' });
				$(".state-no").linkbutton({ text: '否', plain: true, iconCls: 'icon-stop' });
			},
			onSelect:function(rowIndex, rowData){  
				if(rowData.adState == 0){
					$("#stop").show();
					$("#start").hide();
				}else{
					$("#start").show();
					$("#stop").hide();
				}
			},
	});
	//设置分页控件 
	var p = datagrid.datagrid('getPager'); 
	$(p).pagination({ 
		beforePageText: '第',//页数文本框前显示的汉字 
		afterPageText: '页    共 {pages} 页', 
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	}); 
	
	function resetPassword(){
		var selectRows =datagrid.treegrid("getSelections");
		if (selectRows.length < 1) {
			$.messager.alert("提示消息", "请选择要重置密码的管理员!");
			return;
		}
		//提醒用户是否是真的删除数据
		$.messager.confirm("确认消息", "您确定要重置管理员【"+selectRows[0].adName+"】的密码吗？", function (r) {
			if (r) {
				MaskUtil.mask();
				$.ajax({
					url: "admin/admin/ResetAdminPassword",
					type: "post",
					dataType: "json",
					data:{"id": selectRows[0].adId},
					success: function (data) {
						MaskUtil.unmask();
						console.log(data);
						if(data.code == 200){
							//成功
						}else{
							HandleException(data);
						}
					}
				});
			}
		});
	}

	//删除数据
	function doDelete(datagrid) {
		var selectRows =datagrid.treegrid("getSelections");
		if (selectRows.length < 1) {
			$.messager.alert("提示消息", "请选择要删除的用户!");
			return;
		}
		//提醒用户是否是真的删除数据
		$.messager.confirm("确认消息", "您确定要删除管理员【"+selectRows[0].adName+"】吗？", function (r) {
			if (r) {
				MaskUtil.mask();
				$.ajax({
					url: "admin/admin/DeleteAdminById",
					type: "post",
					dataType: "json",
					data:{"id": selectRows[0].adId},
					success: function (data) {
						MaskUtil.unmask();
						if(data.code == 200){
							datagrid.datagrid("reload");
							datagrid.datagrid("clearSelections");
						}else{
							HandleException(data);
						}
					}
				});
			}
		});
	}

	$("#btnSearch").click(function(){
		doSearch();
	});

	function doSearch(){
		$('#user-tb').datagrid('load',{
			name: $('#name').val(),
			loginName: $('#loginName').val()
		});
	}

	$('#user-box').dialog({
		title: '新增管理员',
		width: 800,
		height: 200,
		closed: true,
		cache: false,
		modal: true,
		buttons:[{
			text:'保存',
			iconCls:'icon-ok',
			handler:function(){
				formAddSubmit();
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#user-box').dialog("close");
			}
		}]
	});

	$('#user-detail-box').dialog({
		title: '用户详情',
		width: 800,
		height: 500,
		closed: true,
		cache: false,
		modal: true,
		buttons:[{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				$('#user-detail-box').dialog("close");
			}
		}]
	});

	function formAddSubmit(){
		$('#user-form').form('submit', {
			url:'admin/admin/AddAdmin',
			onSubmit: function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(data){
				$('#user-box').dialog("close");
				$('#user-form').form("clear");
				datagrid.datagrid("reload");
			}
		});
	}

	function showDetail(){
		var selectRows =datagrid.treegrid("getSelections");
		if (selectRows.length < 1) {
			$.messager.alert("提示消息", "请选择要查看的管理员!");
			return;
		}else{
			$.ajax({
				url: "admin/admin/SelectAdminById",
				type: "post",
				dataType: "json",
				data:{"id": selectRows[0].adId},
				success: function (data) {
					if(data.code == 200){
						var user = data.data;
						$(".name").html(user.usName);
						$(".loginName").html(user.usLoginname);
						
						$(".state").html(user.usState==0? "是":"否");
						$(".addDate").html(jsonTimeStamp(user.usRegistdate));
						$(".loginDate").html(jsonTimeStamp(user.usLastlogindate));
						$('#user-detail-box').dialog("open");
					}else{
						HandleException(data);
					}
				}
			});
		}
	}

	function changeState(state){
		var selectRows =datagrid.treegrid("getSelections");
		var str = "";
		if(state == 0){
			str = "启用";
		}else{
			str = "停用";
		}
		$.messager.confirm("确认消息", "您确定要"+str+"帐号【"+selectRows[0].adName+"】吗？", function (r) {
			if (r) {
				$.ajax({
					url:'admin/admin/UpdateAdminState',
					type:'post',
					dataType: 'json',
					data: {  
						"id" : selectRows[0].adId,
						"state":state,
					},
					success:function(data){
						if(data.code== 200){
							datagrid.datagrid("reload");
							datagrid.datagrid("clearSelections");
						}else{
							HandleException(data);
						}
					},
					error:function(e){
						alert(e.message);
					}
				});
			}
		});

	}
});