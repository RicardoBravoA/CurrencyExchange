const express = require("express");
const app = express();

app.listen(3000, () => {
 console.log("The server is running in port 3000");
});

app.get('/api/exchange/:originCurrency/:destinationCurrency', function(req, res) {
    var originCurrency = req.params.originCurrency;
    var destinationCurrency = req.params.destinationCurrency;

    if(originCurrency === destinationCurrency){
        var value;
        switch(originCurrency){
            case "PEN": {
                value = "Soles";
                break;
            }
            case "USD": {
                value = "Dollars";
                break;
            }
            case "EUR": {
                value = "Euros";
                break;
            }
        }
        res.status(200).json({buy:1, sell: 1, multiply: true, buyValue: value, sellValue: value, buyCurrency: originCurrency, sellCurrency: originCurrency});
    }

    //PEN
    if(originCurrency == "PEN" && destinationCurrency == "USD"){
        res.status(200).json({buy:3.698, sell: 3.742, multiply: false, buyValue: "Soles", sellValue: "Dollars", buyCurrency: "PEN", sellCurrency: "USD"});
    }

    if(originCurrency == "PEN" && destinationCurrency == "EUR"){
        res.status(200).json({buy:4.46, sell: 4.5, multiply: false, buyValue: "Soles", sellValue: "Euros", buyCurrency: "PEN", sellCurrency: "EUR"});
    }

    //USD
    if(originCurrency == "USD" && destinationCurrency == "PEN"){
        res.status(200).json({buy:3.698, sell: 3.742, multiply: true, buyValue: "Dollars", sellValue: "Soles", buyCurrency: "USD", sellCurrency: "PEN"});
    }

    if(originCurrency == "USD" && destinationCurrency == "EUR"){
        res.status(200).json({buy:1.2, sell: 1.2, multiply: false, buyValue: "Dollars", sellValue: "Euros", buyCurrency: "USD", sellCurrency: "EUR"});
    }

    //EUR
    if(originCurrency == "EUR" && destinationCurrency == "PEN"){
        res.status(200).json({buy:4.46, sell: 4.5, multiply: true, buyValue: "Euros", sellValue: "Soles", buyCurrency: "EUR", sellCurrency: "PEN"});
    }

    if(originCurrency == "EUR" && destinationCurrency == "USD"){
        res.status(200).json({buy:1.2, sell: 1.2, multiply: true, buyValue: "Euros", sellValue: "Dollars", buyCurrency: "EUR", sellCurrency: "USD"});
    }

    if(originCurrency != "PEN" && originCurrency != "USD" && originCurrency !=  "EUR"){
        res.status(500).json({code:100, message: "Origin currency not found"});
    } else if(destinationCurrency != "PEN" && destinationCurrency != "USD" && destinationCurrency !=  "EUR"){
        res.status(500).json({code:100, message: "Destination currency not found"});
    }

});

app.get('/api/country', function(req, res) {

    res.status(200).json({countries: [{name: "Peru", value: "PEN", currency: "Soles"}, 
        {name: "United States", value: "USD", currency: "Dollars"}, 
        {name: "European Union", value: "EUR",currency: "Euros"}]})
    
});