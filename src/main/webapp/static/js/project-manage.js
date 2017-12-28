/**
 * 
 */

$(function(){
	var datagrid; //定义全局变量datagrid
	var editRow = undefined; //定义全局变量：当前编辑的行
	datagrid = $("#article-tb").datagrid({
		dnd: true,
		method:"POST",
		url:"admin/project/SelectProjectOfAll",
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
			field:'prId',
			title:"项目编号",
			width:100,
			align:'center',
		},{
			field:'prName',
			title:"项目名称",
			width:220,
			align:'center',
		},{
			field:'projectType',
			title:"项目类别",
			width:220,
			align:'center',
			formatter: function(value,row,index){
				return value.ptName;
			}
		},{
			field:'prBudget',
			title:"项目预算",
			width:220,
			align:'center'
		},{
			field:'prExpectDate',
			title:"项目预计周期",
			width:220,
			align:'center'
		},{
			field:'prSignStartDate',
			title:"报名开始时间",
			width:220,
			align:'center',
			formatter: function(value,row,index){
				return jsonTimeStamp(value);
			}
		},{
			field:'prSignEndDate',
			title:"报名结束时间",
			width:220,
			align:'center',
			formatter: function(value,row,index){
				return jsonTimeStamp(value);
			}
		},{
			field:'prState',
			title:"项目状态",
			width:220,
			align:'center',
			formatter: function(val,row,index){
				var str = "";
				if(val == 0){
					str =  "报名未开始";
				}else if(val == 1){
					str = "报名中";
				}else if(val == 2){
					str = "报名结束，待分配承包商";
				}else if(val == 3){
					str = "已分配承包商，待开工";
				}else if(val == 4){
					str = "正在建设中";
				}
				return "<font color='#FF0000'>"+str+"</font>";
			}
		}
		]],
		toolbar:[{
			text:'新增',
			iconCls:'icon-application-form-add',
			handler:function(){
				getProjectType($("#type-combox"));
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

	function getProjectType(combobox){
		combobox.combobox({  
			method:"POST",
			url:'admin/projectType/SelectProjectTypeOfAll',  
			valueField:'ptId',  
			textField:'ptName',
			editable:false,
			loadFilter: function(data){
				if (data.code == 200){
					return data.data;
				}else{
					HandleException(data);
				}
			},
			onLoadSuccess: function () { 
				var data = $(this).combobox("getData");
				if(data.length > 0){
					$(this).combobox("select", data[0].ptId);
				}
			}
		});  
	}
	
	//删除数据
	function doDelete(datagrid) {
		var selectRows =datagrid.treegrid("getSelections");
		if (selectRows.length < 1) {
			$.messager.alert("提示消息", "请选择要删除的项目!");
			return;
		}
		//提醒用户是否是真的删除数据
		$.messager.confirm("确认消息", "您确定要删除项目【"+selectRows[0].prName+"】吗？", function (r) {
			if (r) {
				MaskUtil.mask();
				$.ajax({
					url: "admin/project/DeleteProjectById",
					type: "post",
					dataType: "json",
					data:{"id": selectRows[0].prId},
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
		title: '项目新增',
		width: 1000,
		height: 750,
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
			url:'admin/project/AddProject',
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
			$.messager.alert("提示消息", "请选择要查看的项目!");
			return;
		}else if(selectRows.length > 1){
			$.messager.alert("提示消息", "只能选择一条的记录!");
			return;
		}else{
			$.ajax({
				url: "admin/project/SelectProjectById",
				type: "post",
				dataType: "json",
				data:{"id": selectRows[0].prId},
				success: function (data) {
					console.log(data);
					if(data.code == 200){
						var project = data.data;
						console.log(project);
						$(".prName").html(project.prName);
						$(".prType").html(project.prName);
						$(".prBudget").html(project.prBudget);
						$(".prExpectDate").html(project.prExpectDate);
						$(".prDescription").html(project.prDescription);
						$(".prContacts").html(project.prContacts);
						$(".prContactsPhone").html(project.prContactsPhone);
						$(".prSignStartDate").html(jsonTimeStamp(project.prSignStartDate));
						$(".prSignEndDate").html(jsonTimeStamp(project.prSignEndDate));
						$('#article-detail-box').dialog("open");
					}else{
						HandleException(data);
					}
				}
			});
		}
	}
	
	$('#article-detail-box').dialog({
		title: '项目详情',
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