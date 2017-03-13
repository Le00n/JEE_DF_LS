function calculateTicketCosts() {
	var amountNormalTickets = $(".ticketInput").eq(0).val();
	var amountPremiumTickets = $(".ticketInput").eq(1).val();
	
	var priceNormalTickets = $(".ticketPrice")[0].dataset.price;
	var pricePremiumTickets = $(".ticketPrice")[1].dataset.price;
	
	var sum = 0;
	if(amountNormalTickets && amountNormalTickets >= 1 && priceNormalTickets)
	{
		sum += amountNormalTickets * priceNormalTickets;
	}
	
	if(amountPremiumTickets && amountPremiumTickets >= 1 && pricePremiumTickets)
	{
		sum += amountPremiumTickets * pricePremiumTickets;
	}
	
	 $("#sumPrice").text(sum.toFixed(2) + " €");
}

/* bei Änderungen in der Klasse: Start der Berechnung */
$(".ticketInput").change(function(){
	calculateTicketCosts();
});