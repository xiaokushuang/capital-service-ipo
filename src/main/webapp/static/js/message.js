var socket;
var topicName;

var socketCreator = function(url, u, p, path) {
	if (!top.window.socket) {
		var ws = new SockJS(url);
		var client = Stomp.over(ws);
		client.heartbeat.outgoing = 0;
		client.heartbeat.incoming = 0;
		client.connect(u, p, on_connect, on_error, path);
		top.window.socket = client;
	}
	return top.window.socket;
}

var on_connect = function() {
	onOpen();
	top.window.socket.subscribe("/topic/"+topicName, onMessage);
	//sendToServerMessage({companyId:"96392852564612838", userId:"1"});
};

var onOpen = function() {
	//clearInterval(socketInterval);
}

var onMessage = function(m) {
	var msg = $.parseJSON(m.body);
	//console.log("received message:", msg);
	var $messageCountArea = $("#messageCount1", top.window.document);
	var oldMsgNumber = $messageCountArea.text();
	var $messageCountArea1 = $("#messageCount2", top.window.document);
	var oldMsgNumber1 = $messageCountArea1.text();
	if("" == oldMsgNumber) {
		$messageCountArea.text(1);
		$messageCountArea.css("background", "red");
	} else {
		if(oldMsgNumber == "99+"){
			$messageCountArea.text("99+");
		}else{
//			$messageCountArea.text(Number(oldMsgNumber.substring(0,3))+1);
//			if((Number(oldMsgNumber.substring(0,3))+1)>99){
//				$messageCountArea.text("99+");
//			}
			if(oldMsgNumber=="99"){
				$messageCountArea.text("99+");
			}else{
				$messageCountArea.text(Number(oldMsgNumber)+1);
			}
		}
		
		$messageCountArea.css("background", "red");
	}
	if("" == oldMsgNumber1) {
		$messageCountArea1.text(1);
		$messageCountArea1.css("background", "red");
	} else {
		if(oldMsgNumber1 == "99+"){
			$messageCountArea1.text("99+");
		}else{
//			$messageCountArea1.text(Number(oldMsgNumber1.substring(0,3))+1);
//			if((Number(oldMsgNumber1.substring(0,3))+1)>99){
//				$messageCountArea1.text("99+");
//			}
			if(oldMsgNumber1=="99"){
				$messageCountArea1.text("99+");
			}else{
				$messageCountArea1.text(Number(oldMsgNumber1)+1);
			}
		}
		
		$messageCountArea1.css("background", "red");
	}
	popoverHot1(1);
	top.window.popMsg("您有1条新的消息", "rb");
}

var on_error =  function(error) {
	//console.log(error);
};
/**
 * 实时发送消息到服务器(JSON)
 * @param msg
 */
function sendToServerMessage(msg) {
	top.window.socket.send("/queue/company", {"content-type":"application/json"}, JSON.stringify(msg));
}