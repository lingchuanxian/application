/**
 * 
 */

$(function(){
	var datagrid; //定义全局变量datagrid
	var editRow = undefined; //定义全局变量：当前编辑的行
	datagrid = $("#article-tb").datagrid({
		dnd: true,
		method:"POST",
		url:"admin/projectGroup/GetProjectGroupList",
		idField:'artId',
		rownumbers: true,
		checkOnSelect : true,  
		width: $(window).width() - 6,
		height: $(window).height() - 110,
		striped: true, //行背景交换
		fitColumns:false,
		pagination:true,//分页控件 
		singleSelect:true,
		pageSize: 20,//每页显示的记录条数，默认为10 
		pageList: [10,20,50,100],//可以设置每页记录条数的列表 
		loadFilter: function(data){
			if (data.code == 200){
				return data.data;
			}else{
				alert("1:"+data.message);
			}
		},
		loadFilter: function(data){
			console.log(data);
			if (data.code == 200){
				return data.data;
			}else{
				alert("1:"+data.message);
			}
		},
		columns:[[{
			field:'pgId',
			title:"工程队编号",
			width:100,
			align:'center',
		},{
			field:'pgName',
			title:"工程队名称",
			width:220,
			align:'center',
		},{
			field:'pgScale',
			title:"工程队规模",
			width:220,
			align:'center',
		},{
			field:'pgLeader',
			title:"负责人",
			width:220,
			align:'center'
		},{
			field:'pgLeaderPhone',
			title:"负责人联系方式",
			width:220,
			align:'center',
		}
		]],
		toolbar:[{
			text:'新增',
			iconCls:'icon-application-form-add',
			handler:function(){
				$('#artContent').val("");
				$("#article-form").form("disableValidation");
				$('#article-add-box').dialog("open");
			}
		},'-',{
			text:'编辑',
			iconCls:'icon-application-form-edit',
			handler:function(){
				showArticleDetail();
			}
		},'-',{
			text:'删除',
			iconCls:'icon-application-form-delete',
			handler:function(){
				doDelete(datagrid);
			}
		},'-',{
			text:'查看',
			iconCls:'icon-application-form-magnify',
			handler:function(){
				showArticleDetail();
			}
		}],
		onLoadSuccess: function(row){
			datagrid.datagrid("unselectAll");
		},
	});

	//设置分页控件 
	var p = datagrid.datagrid('getPager'); 
	$(p).pagination({ 
		beforePageText: '第',//页数文本框前显示的汉字 
		afterPageText: '页    共 {pages} 页', 
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	}); 


	//删除数据
	function doDelete(datagrid) {
		var selectRows =datagrid.treegrid("getSelections");
		if (selectRows.length < 1) {
			$.messager.alert("提示消息", "请选择要删除的工程队!");
			return;
		}
		//提醒用户是否是真的删除数据
		$.messager.confirm("确认消息", "您确定要删除工程队【"+selectRows[0].pgName+"】吗？", function (r) {
			if (r) {
				MaskUtil.mask();
				$.ajax({
					url: "admin/projectGroup/DeleteProjectGroupById",
					type: "post",
					dataType: "json",
					data:{"id": selectRows[0].pgId},
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

	$('#article-add-box').dialog({
		title: '工程队新增',
		width: 1000,
		height: 800,
		closed: true,
		cache: false,
		modal: true,
		buttons:[{
			text:'提交',
			iconCls:'icon-ok',
			handler:function(){
				formAddSubmit();
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#article-add-box').dialog("close");
			}
		}]
	});

	function formAddSubmit(){
		$('#article-form').form('submit', {
			url:'admin/projectGroup/AddProjectGroup',
			onSubmit: function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(data){
				$('#article-form').form('clear');
				$("#pgBrief").val("");
				$('#article-add-box').dialog("close");
				datagrid.datagrid("reload");
			},
			error:function(){
				alert("error");
			}
		});
	}

	$("#search-type").combobox({
		onChange: function (n,o) {
			if($(this).val() == 0){
				$(".td_title").show();
				$(".td_type").hide();
			}else if($(this).val() == 1){
				$(".td_title").hide();
				$(".td_type").show();
			}
		}
	});
	
	$("#btnSearch").click(function(){
		doSearch();
	});

	function doSearch(){
		$('#article-tb').datagrid('load',{
			name: $('#name').val(),
		});
	}
	
	function showArticleDetail(){
		var selectRows =datagrid.treegrid("getSelections");
		if (selectRows.length < 1) {
			$.messager.alert("提示消息", "请选择要查看的文章!");
			return;
		}else if(selectRows.length > 1){
			$.messager.alert("提示消息", "只能选择一条的记录!");
			return;
		}else{
			$.ajax({
				url: "admin/projectGroup/SelectProjectGroupById",
				type: "post",
				dataType: "json",
				data:{"id": selectRows[0].pgId},
				success: function (data) {
					console.log(data);
					if(data.code == 200){
						var group = data.data;
						$(".pgName").html(group.pgName);
						$(".pgScale").html(group.pgScale);
						$(".pgAddress").html(group.pgAddress);
						$(".pgLeader").html(group.pgLeader);
						$(".pgLeaderPhone").html(group.pgLeaderPhone);
						$(".pgLeaderEmail").html(group.pgLeaderEmail);
						$(".pgBrief").html(group.pgBrief);
						$('#article-detail-box').dialog("open");
					}else{
						HandleException(data);
					}
				}
			});
		}
	}
	
	$('#article-detail-box').dialog({
		title: '工程队详情',
		width: 1000,
		height: 800,
		closed: true,
		cache: false,
		modal: true,
		buttons:[{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				$('#article-detail-box').dialog("close");
			}
		}]
	});
	
	
});