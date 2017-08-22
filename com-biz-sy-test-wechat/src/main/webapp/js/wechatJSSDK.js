/*
 * 注意：
 *  所有的 JS 接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS 接口安全域名”。
 */
//1. 判断当前版本是否支持指定 JS 接口，支持批量判断，只需要将需要判断的接口放入到 jsApiList 中即可
function checkJsApifunction() {
	wx.checkJsApi({
		jsApiList : [ 'getNetworkType', 'previewImage' ],
		success : function(res) {
			alert(JSON.stringify(res));
		}
	});
};
// 2. 分享接口
// 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
function onMenuShareAppMessagefunction() {
	wx.onMenuShareAppMessage({
		title : '菜鸟程序员成长之路！',
		desc : '关注 java 平台开发，前后端框架技术，分享前后端开发资源，服务端教程技术，菜鸟程序员！',
		link : 'http://www.cuiyongzhi.com/',
		imgUrl : 'http://res.cuiyongzhi.com/2016/03/201603201591_339.png',
		trigger : function(res) {
			// 不要尝试在 trigger 中使用 ajax 异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用 ajax
			// 的回包会还没有返回
			alert('用户点击发送给朋友');
		},
		success : function(res) {
			alert('已分享');
		},
		cancel : function(res) {
			alert('已取消');
		},
		fail : function(res) {
			alert(JSON.stringify(res));
		}
	});
	alert('已注册获取“发送给朋友”状态事件');
};

// 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
function onMenuShareTimelinefunction() {
	wx.onMenuShareTimeline({
		title : '菜鸟程序员成长之路！',
		link : 'http://www.cuiyongzhi.com/',
		imgUrl : 'http://res.cuiyongzhi.com/2016/03/201603201591_339.png',
		trigger : function(res) {
			// 不要尝试在 trigger 中使用 ajax 异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用 ajax
			// 的回包会还没有返回
			alert('用户点击分享到朋友圈');
		},
		success : function(res) {
			alert('已分享');
		},
		cancel : function(res) {
			alert('已取消');
		},
		fail : function(res) {
			alert(JSON.stringify(res));
		}
	});
	alert('已注册获取“分享到朋友圈”状态事件');
};

// 2.3 监听“分享到 QQ”按钮点击、自定义分享内容及分享结果接口
function onMenuShareQQfunction() {
	wx.onMenuShareQQ({
		title : '菜鸟程序员成长之路！',
		desc : '关注 java 平台开发，前后端框架技术，分享前后端开发资源，服务端教程技术，菜鸟程序员！',
		link : 'http://www.cuiyongzhi.com/',
		imgUrl : 'http://res.cuiyongzhi.com/2016/03/201603201591_339.png',
		trigger : function(res) {
			alert('用户点击分享到 QQ');
		},
		complete : function(res) {
			alert(JSON.stringify(res));
		},
		success : function(res) {
			alert('已分享');
		},
		cancel : function(res) {
			alert('已取消');
		},
		fail : function(res) {
			alert(JSON.stringify(res));
		}
	});
	alert('已注册获取“分享到 QQ”状态事件');
};

// 2.4 监听“分享到微博”按钮点击、自定义分享内容及分享结果接口
function onMenuShareWeibofunction() {
	wx.onMenuShareWeibo({
		title : '菜鸟程序员成长之路！',
		desc : '关注 java 平台开发，前后端框架技术，分享前后端开发资源，服务端教程技术，菜鸟程序员！',
		link : 'http://www.cuiyongzhi.com/',
		imgUrl : 'http://res.cuiyongzhi.com/2016/03/201603201591_339.png',
		trigger : function(res) {
			alert('用户点击分享到微博');
		},
		complete : function(res) {
			alert(JSON.stringify(res));
		},
		success : function(res) {
			alert('已分享');
		},
		cancel : function(res) {
			alert('已取消');
		},
		fail : function(res) {
			alert(JSON.stringify(res));
		}
	});
	alert('已注册获取“分享到微博”状态事件');
};

// 2.5 监听“分享到 QZone”按钮点击、自定义分享内容及分享接口
function onMenuShareQZonefunction() {
	wx.onMenuShareQZone({
		title : '菜鸟程序员成长之路！',
		desc : '关注 java 平台开发，前后端框架技术，分享前后端开发资源，服务端教程技术，菜鸟程序员！',
		link : 'http://www.cuiyongzhi.com/',
		imgUrl : 'http://res.cuiyongzhi.com/2016/03/201603201591_339.png',
		trigger : function(res) {
			alert('用户点击分享到 QZone');
		},
		complete : function(res) {
			alert(JSON.stringify(res));
		},
		success : function(res) {
			alert('已分享');
		},
		cancel : function(res) {
			alert('已取消');
		},
		fail : function(res) {
			alert(JSON.stringify(res));
		}
	});
	alert('已注册获取“分享到 QZone”状态事件');
};
// 3 设备信息接口
// 3.1 获取当前网络状态
function getNetworkTypefunction() {
	wx.getNetworkType({
		success : function(res) {
			alert(res.networkType);
			var networkType = res.networkType; // 返回网络类型 2g，3g，4g，wifi
			if (networkType == '3g') {
				alert("您好，您的网络状态是 3g 网络，这里将播放视频文件会产生大流程!");
			}
		},
		fail : function(res) {
			alert(JSON.stringify(res));
		}
	});
};
// 4 地理位置接口
// 4.1 查看地理位置
function openLocationfunction() {
	wx.openLocation({
		latitude : 23.099994,
		longitude : 113.324520,
		name : 'TIT 创意园',
		address : '广州市海珠区新港中路 397 号',
		scale : 14,
		infoUrl : 'http://weixin.qq.com'
	});
};

// 4.2 获取当前地理位置
function getLocationfunction() {
	wx.getLocation({
		success : function(res) {
			alert(JSON.stringify(res));
		},
		cancel : function(res) {
			alert('用户拒绝授权获取地理位置');
		}
	});
};
// 5 界面操作接口
// 5.1 隐藏右上角菜单
function hideOptionMenufunction() {
	wx.hideOptionMenu();
};

// 5.2 显示右上角菜单
function showOptionMenufunction() {
	wx.showOptionMenu();
};

// 5.3 批量隐藏菜单项
function hideMenuItemsfunction() {
	wx.hideMenuItems({
		menuList : [ 'menuItem:readMode', // 阅读模式
		'menuItem:share:timeline', // 分享到朋友圈
		'menuItem:copyUrl' // 复制链接
		],
		success : function(res) {
			alert('已隐藏“阅读模式”，“分享到朋友圈”，“复制链接”等按钮');
		},
		fail : function(res) {
			alert(JSON.stringify(res));
		}
	});
};

// 5.4 批量显示菜单项
function showMenuItemsfunction() {
	wx.showMenuItems({
		menuList : [ 'menuItem:readMode', // 阅读模式
		'menuItem:share:timeline', // 分享到朋友圈
		'menuItem:copyUrl' // 复制链接
		],
		success : function(res) {
			alert('已显示“阅读模式”，“分享到朋友圈”，“复制链接”等按钮');
		},
		fail : function(res) {
			alert(JSON.stringify(res));
		}
	});
};

// 5.5 隐藏所有非基本菜单项
function hideAllNonBaseMenuItemfunction() {
	wx.hideAllNonBaseMenuItem({
		success : function() {
			alert('已隐藏所有非基本菜单项');
		}
	});
};

// 5.6 显示所有被隐藏的非基本菜单项
function showAllNonBaseMenuItemfunction() {
	wx.showAllNonBaseMenuItem({
		success : function() {
			alert('已显示所有非基本菜单项');
		}
	});
};

// 5.7 关闭当前窗口
function closeWindowfunction() {
	wx.closeWindow();
};
// 6 微信原生接口
function scanQRCode() {
	wx.scanQRCode({
		needResult : 0, // 默认为 0，扫描结果由微信处理，1 则直接返回扫描结果，
		scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
		success : function(res) {
			var result = res.resultStr; // 当 needResult 为 1 时，扫码返回的结果
		}
	});
}