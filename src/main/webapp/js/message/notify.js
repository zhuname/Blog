$(function(){
	var notifyInterval;
	var notifyNowBack;
	setInterval(function(){
		$.ajax({
			url:'/mts/system/notification/list/json',
			success:function(result){
				//data = JSON.parse(result);
				$('#posterpackageCount').html(result.data.posterpackageCount);
				$('#mediapackageCount').html(result.data.mediapackageCount);
				$('#cardCount').html(result.data.cardCount);
				$('#applyMedalCount').html(result.data.applyMedalCount);
				$('#applyWithdrawCount').html(result.data.applyWithdrawCount);
				$('#sumStatics').html(result.data.sumCount);
				$('#sumCount').html(result.data.sumCount);
				
			}
		});
	
	},50);
});