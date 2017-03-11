function calculateTicketCosts() {
		var amountNormalTickets = $(".ticketInput").get(0).val();
		var amountPremiumTickets = $(".ticketInput").get(1).val();
		alert(amountNormalTickets);
		alert(amountPremiumTickets);

		//Preis aller Tickets berechnen
		var sum = parseInt(amountNormalTickets) + parseInt(amountPremiumTickets); 
		alert(sum);
		
	    $("#sumPrice").val(sum);
}

/* bei Änderungen in der classe start Berechnung */
$(".ticketInput").change(function(){
	calculateTicketCosts();
});